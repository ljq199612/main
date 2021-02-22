package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.WorkflowParam;
import com.rz.iot.bpo.model.show.workflow.WorkflowConfigShow;
import com.rz.iot.bpo.service.SysUserService;
import com.rz.iot.bpo.service.WorkflowConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workflow")
public class WorkflowConfigController extends BaseController {

    @Resource
    private WorkflowConfigService workflowConfigService;

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/add")
    @Log(title = "新增流程",businessType = BusinessType.INSERT)
    public Result add(@RequestBody WorkflowParam workflowParam) {
        workflowConfigService.add(workflowParam);
        return Result.success();
    }

    @GetMapping("/findAll")
    public Result findAll(Map<String,Object> params) {
        startPage();
        List<WorkflowConfigShow> list = workflowConfigService.findAll(params);
        return getDataTable(list);
    }

    @PostMapping("/update")
    @Log(title = "修改流程",businessType = BusinessType.UPDATE)
    public Result update(@RequestBody WorkflowParam workflowParam) {
        workflowConfigService.update(workflowParam);
        return Result.success();
    }

    @GetMapping("/delete")
    @Log(title = "删除流程",businessType = BusinessType.DELETE)
    public Result delete(Integer id) {
        return Result.success();
    }

    @GetMapping("/configDetail")
    public Result configDetail(Integer id) {
        WorkflowConfigShow configShow = workflowConfigService.configDetail(id);
        return Result.success(configShow);
    }

    /**
     * 查询项目下所有流程审核人员
     */
    @RequestMapping("/selectByProject")
    public Result selectAuditorByProject(Integer projectId) {
        List<Map<String,Object>> list = sysUserService.selectAuditorByProject(projectId);
        return Result.success(list);
    }
}
