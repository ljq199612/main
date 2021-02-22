package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.show.workflow.WorkflowInstanceShow;
import com.rz.iot.bpo.model.show.workflow.WorkflowShow;
import com.rz.iot.bpo.service.WorkflowInstanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class WorkflowInstanceServiceImpl implements WorkflowInstanceService {

    @Resource
    private WorkflowConfigMapper configMapper;

    @Resource
    private WorkflowNodeMapper nodeMapper;

    @Resource
    private WorkflowInstanceMapper instanceMapper;

    @Resource
    private WorkflowNodeInstanceMapper nodeInstanceMapper;

    @Resource
    private WorkflowInfoMapper infoMapper;

    @Resource
    private WorkflowLogMapper logMapper;

    @Resource
    private BpoAttendanceInfoMapper attendanceInfoMapper;

    @Resource
    private BpoPersonMapper personMapper;

    @Resource
    private SysCompanyMapper companyMapper;

    @Resource
    private BpoBillSupplierMonthMapper billSupplierMonthMapper;

    @Override
    public List<WorkflowNodeInstance> processProgress(Integer infoId, Byte type) {
        List<WorkflowNodeInstance> nodeInstanceList = infoMapper.selectNodeInstanceList(infoId, type);
        return nodeInstanceList;
    }

    @Override
    public List<WorkflowShow> getWorkflowNodeInstanceList(Byte type) {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<WorkflowShow> list = instanceMapper.getWorkflowNodeInstanceList(type,userId);
        return list;
    }

    @Override
    public List<WorkflowInstance> getWorkflowInstanceList() {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<WorkflowInstance> list = instanceMapper.getWorkflowInstanceList(userId);
        return list;
    }

    @Override
    public WorkflowInstanceShow getWorkflowDetail(Integer id) {
        WorkflowInstanceShow instanceShow = instanceMapper.getWorkflowDetail(id);
        return instanceShow;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void approve(Map<String,Object> params) {
        Byte assigneeResult = Byte.parseByte(params.get("assigneeResult").toString());
        String assigneeSuggestion = params.get("assigneeSuggestion") != null ? params.get("assigneeSuggestion").toString() : "";
        Integer infoId = Integer.parseInt(params.get("id").toString());
        Byte type = Byte.parseByte(params.get("type").toString());

        WorkflowNodeInstance nodeInstance = nodeInstanceMapper.selectByInfoId(infoId,type);
        if (nodeInstance == null) {
            throw new RuntimeException("不存在进行中的流程");
        }
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        if (!userId.equals(nodeInstance.getAssigneeUserId())) {
            throw new RuntimeException("没有审核权限！");
        }

        //审核
        if (assigneeResult.equals(DictDataConstants.WORKFLOW_APPROVE_CONFIRM)) {
            nodeInstance.setStatus(DictDataConstants.WORKFLOW_STATUS_FINISHED);
        } else if (assigneeResult.equals(DictDataConstants.WORKFLOW_APPROVE_REJECT)) {
            nodeInstance.setStatus(DictDataConstants.WORKFLOW_STATUS_REJECT);
        } else if (assigneeResult.equals(DictDataConstants.WORKFLOW_APPROVE_FINISH)) {
            nodeInstance.setStatus(DictDataConstants.WORKFLOW_STATUS_FINISHED);
            //TODO
        } else {
            return;
        }
        nodeInstanceMapper.updateByPrimaryKeySelective(nodeInstance);
        //保存流程日志
        saveLog(nodeInstance.getInstanceId(),nodeInstance.getId(),assigneeSuggestion,assigneeResult);
        //处理下个节点
        WorkflowNode node = nodeMapper.selectByPrimaryKey(nodeInstance.getNodeId());
        NextNodeHandle(node,assigneeResult,nodeInstance.getInstanceId());
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void generateInstance(Byte type, Integer workflowId, Integer infoId) {
        if (type.equals(DictDataConstants.WORKFLOW_ATTENDANCE)) {
            generateAttendanceInstance(infoId);
        }
        if (type.equals(DictDataConstants.WORKFLOW_ACCOUNT_RECEIVABLE)) {
            generateAccountReceivableInstance(workflowId,infoId);
        }
        if (type.equals(DictDataConstants.WORKFLOW_ACCOUNT_PAYABLE)) {
            generateAccountReceivableInstance(workflowId,infoId);
        }
    }

    /**
     * 生成应收款流程实例
     */
    private void generateAccountReceivableInstance(Integer workflowId,Integer infoId) {
        //获取生成流程的数据
        BpoBillSupplierMonth supplierMonth = billSupplierMonthMapper.selectByPrimaryKey(infoId);
        if (supplierMonth == null) {
            throw new RuntimeException("账单不存在！");
        }
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();

        //根据workflowId获取流程
        WorkflowConfig config = configMapper.selectByPrimaryKey(workflowId);
        //生成流程实例
        Integer instanceId = saveInstance(config,"我也不知道用用户名还是人员名");
        //生成流程信息
        saveInfo(instanceId,infoId,DictDataConstants.WORKFLOW_ACCOUNT_RECEIVABLE);
        //获取该流程下的所有节点
        List<WorkflowNode> nodeList = nodeMapper.selectByConfigId(config.getId());
        //遍历每个节点，为每个节点生成节点实例
        for (WorkflowNode node: nodeList) {
            saveNodeInstance(node,instanceId);
        }
    }

    /**
     * 生成考勤实例
     * @param infoId
     */
    private void generateAttendanceInstance(Integer infoId) {
        BpoAttendanceInfo attendanceInfo = attendanceInfoMapper.selectByPrimaryKey(infoId);
        if (attendanceInfo == null) {
            return;
        }
        Integer projectId = attendanceInfo.getProjectId();
        BpoPerson person = personMapper.selectByPrimaryKey(attendanceInfo.getPersonId());
        SysCompany company = companyMapper.selectByPrimaryKey(person.getCompanyId());
        WorkflowConfig workflowConfig = configMapper.selectByProjectSupplierId(projectId,company.getId());
        if (workflowConfig == null) {
            return;
        }
//        WorkflowNode workflowNode = nodeMapper.selectFirstNode(workflowConfig.getId());
//        if (workflowNode == null) {
//            return;
//        }

        //保存实例,获取实例ID
        Integer instanceId = saveInstance(workflowConfig,"系统生成");

        //保存流程信息
        saveInfo(instanceId,attendanceInfo.getId(),DictDataConstants.WORKFLOW_ATTENDANCE);

        //保存节点实例
//        saveNodeInstance(workflowNode,instanceId);

        //获取该流程下的所有节点
        List<WorkflowNode> nodeList = nodeMapper.selectByConfigId(workflowConfig.getId());
        //遍历每个节点，为每个节点生成节点实例
        for (WorkflowNode node: nodeList) {
            saveNodeInstance(node,instanceId);
        }
    }

    private Integer saveInstance(WorkflowConfig workflowConfig,String owner) {
        WorkflowInstance instance = new WorkflowInstance();
        instance.setConfigId(workflowConfig.getId());
        instance.setCode(String.valueOf(System.currentTimeMillis()));
        instance.setName(workflowConfig.getWfName());
        instance.setOwner(owner);
        instance.setStatus(DictDataConstants.WORKFLOW_STATUS_ONGOING);
        instanceMapper.insertSelective(instance);
        Integer instanceId = instance.getId();
        return instanceId;
    }

    private void saveInfo(Integer instanceId,Integer infoId,Byte type) {
        WorkflowInfo info = new WorkflowInfo();
        info.setInstanceId(instanceId);
        info.setType(type);
        info.setInfoId(infoId);
        infoMapper.insertSelective(info);
    }

    private void saveNodeInstance(WorkflowNode workflowNode,Integer instanceId) {
        WorkflowNodeInstance nodeInstance = new WorkflowNodeInstance();
        nodeInstance.setName(workflowNode.getNodeName());
        nodeInstance.setInstanceId(instanceId);
        nodeInstance.setNodeId(workflowNode.getId());
        nodeInstance.setAssigneeUserId(workflowNode.getUserId());
        if (workflowNode.getPreId() == null) {
            nodeInstance.setStatus(DictDataConstants.WORKFLOW_STATUS_ONGOING);
        } else {
            nodeInstance.setStatus(DictDataConstants.WORKFLOW_STATUS_WAITING);
        }
        nodeInstanceMapper.insertSelective(nodeInstance);
    }

    /**
     * 记录流程日志
     * @param instanceId
     * @param nodeInstanceId
     * @param assigneeSuggestion
     * @param assigneeResult
     */
    private void saveLog(Integer instanceId,Integer nodeInstanceId,String assigneeSuggestion,Byte assigneeResult) {
        WorkflowLog log = new WorkflowLog();
        log.setInstanceId(instanceId);
        log.setNodeInstanceId(nodeInstanceId);
        log.setAssigneeSuggestion(assigneeSuggestion);
        log.setAssigneeResult(assigneeResult);
        logMapper.insertSelective(log);
    }

    /**
     * 根据节点和审核类型进行下一步处理
     */
    private void NextNodeHandle(WorkflowNode node,Byte assigneeResult,Integer instanceId) {
        WorkflowNode nextNode = null;
        boolean flag = true;
        if (assigneeResult.equals(DictDataConstants.WORKFLOW_APPROVE_CONFIRM)) {
            if (node.getNextId() == null) {
                finishInstance(instanceId);
                flag = false;
            } else {
                nextNode = nodeMapper.selectByPrimaryKey(node.getNextId());
            }
        } else if (assigneeResult.equals(DictDataConstants.WORKFLOW_APPROVE_REJECT)) {
            if (node.getPreId() == null) {
                finishInstance(instanceId);
                flag = false;
            } else {
                nextNode = nodeMapper.selectByPrimaryKey(node.getPreId());
            }
        } else if (assigneeResult.equals(DictDataConstants.WORKFLOW_APPROVE_FINISH)) {
            finishInstance(instanceId);
            flag = false;
        } else {
            throw new RuntimeException("审核类型异常！");
        }

        if (flag) {
            WorkflowNodeInstance nextNodeInstance = nodeInstanceMapper.selectByNodeId(nextNode.getId(),instanceId);
            nextNodeInstance.setStatus(DictDataConstants.WORKFLOW_STATUS_ONGOING);
            nodeInstanceMapper.updateByPrimaryKeySelective(nextNodeInstance);
        }

//        if (flag) {
//            WorkflowNodeInstance nextNodeInstance = new WorkflowNodeInstance();
//            nextNodeInstance.setName(nextNode.getNodeName());
//            nextNodeInstance.setNodeId(nextNode.getId());
//            nextNodeInstance.setStatus(DictDataConstants.WORKFLOW_STATUS_ONGOING);
//            nextNodeInstance.setInstanceId(instanceId);
//            nextNodeInstance.setAssigneeUserId(nextNode.getUserId());
//            nodeInstanceMapper.insertSelective(nextNodeInstance);
//        }
    }

    /**
     * 结束实例
     * @param instanceId
     */
    private void finishInstance(Integer instanceId) {
        WorkflowInstance instance = instanceMapper.selectByPrimaryKey(instanceId);
        instance.setStatus(DictDataConstants.WORKFLOW_STATUS_FINISHED);
        instanceMapper.updateByPrimaryKeySelective(instance);
    }
}
