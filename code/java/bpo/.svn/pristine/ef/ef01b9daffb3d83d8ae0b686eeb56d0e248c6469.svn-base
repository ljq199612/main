package com.rz.iot.bpo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.BpoFeeRuleParam;
import com.rz.iot.bpo.model.param.BpoProcessParam;
import com.rz.iot.bpo.model.param.BpoProductParam;
import com.rz.iot.bpo.model.param.SupplierAddParam;
import com.rz.iot.bpo.model.show.*;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import com.rz.iot.bpo.service.ProjectService;
import com.rz.iot.bpo.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rz.iot.bpo.service.SupplierService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 描述 : 供应商管理 ServiceImpl
 * 作者 : qin xian
 * 创建时间 : 2020/6/18 0018 10:17
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Slf4j
@Service("SupplierService")
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private BpoSupplierInfoMapper bpoSupplierInfoMapper;

    @Resource
    private BpoAccountInfoMapper bpoAccountInfoMapper;

    @Resource
    private BpoFeeRuleMapper bpoFeeRuleMapper;

    @Resource
    private BpoFeeRuleByTimeMapper bpoFeeRuleByTimeMapper;

    @Resource
    private BpoProductPriceMapper bpoProductPriceMapper;

    @Resource
    private ProjectService projectService;

    @Resource
    private SysDeptService sysDeptService;

    @Resource
    private SysCompanyFileMapper sysCompanyFileMapper;

    @Resource
    private SysCompanyMapper sysCompanyMapper;

    @Resource
    private BpoSupplierMapper bpoSupplierMapper;

    @Resource
    private SysDeptUserRelMapper sysDeptUserRelMapper;

    @Resource
    private SysDeptSubRelMapper sysDeptSubRelMapper;

    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT)
    @Override
    public Result list(BaseEntity param,SupplierListShow info,Page<SupplierListShow> page) {

        Result result = new Result(ResultConstants.REQUEST_SUCCESS);


        PageHelper.startPage(page.getPageNum(),page.getPageSize());

        List<SupplierListShow> list = bpoSupplierInfoMapper.list(info,param);

        for (SupplierListShow temp:list){
            if(temp.getBpoSupplier()!=null && temp.getBpoSupplier().getSupplierDeptId()!=null){
                SysDeptShow sysDeptShow = sysDeptService.getDeptAll(temp.getBpoSupplier().getSupplierDeptId());
                temp.setDeptInfo(sysDeptShow);
            }
        }

        PageInfo<SupplierListShow> pageInfo = new PageInfo<>(list);

        page.setList(list);
        page.setTotal (pageInfo.getTotal ());
        page.setPages (pageInfo.getPages ());

        result.setData(page);
        return result;
    }

    @Override
    public Result<SupplierDetailShow> detail(Integer id) {
        if(id == null)
            return Result.requestParamError();

        SupplierDetailShow info = bpoSupplierInfoMapper.detail(id);

        //填充部门信息
        if(info != null && info.getSupplierDeptId() != null){
            SysDeptShow sysDeptShow = sysDeptService.getDeptAll(info.getSupplierDeptId());
            info.setDeptInfo(sysDeptShow);
        }


        return Result.success(info);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result add(SupplierAddParam info) {

        //新增供应商(公司/组织) 获取供应商(公司/组织)Id
        sysCompanyMapper.insertSelective(info);

        //新增供应商详情表
        bpoSupplierMapper.insertSelective(info.toBpoSupplier());


        List<SysCompanyFile> fileList = info.getSysCompanyFiles();
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        for (SysCompanyFile temp : fileList){//初始化
            temp.setCompanyId(info.getId());//供应商(公司/组织)
            temp.setUserId(userId);//文件上传操作人
        }

        //批量文件新增
        sysCompanyFileMapper.insertFileList(fileList);

        // TODO: 2020/7/21 0021 将这部分内容 改造为service中方法提供调用
        // 通过用户查询部门
        SysDeptUserRel sysDeptUserRel = sysDeptUserRelMapper.findByUserId(userId);
        // 新增部门(供应商)与数据关联表
        SysDeptSubRel sysDeptSubRel = new SysDeptSubRel();
        sysDeptSubRel.setDeptId(sysDeptUserRel.getDeptId());
        sysDeptSubRel.setSubId(info.getId());
        sysDeptSubRel.setSubType(DictDataConstants.DEPT_SUB_REL_CUSTOM);
        sysDeptSubRelMapper.insertSelective(sysDeptSubRel);

        //新增下级供应商顶级部门
        SysCompany sysCompany = sysCompanyMapper.selectByPrimaryKey (info.getId ());
        SysDept dept = new SysDept ();
        dept.setDeptName (sysCompany.getCompanyName ());
        dept.setParentId (sysCompany.getId ());
        dept.setPhone (sysCompany.getPhone ());
        dept.setStatus (sysCompany.getStatus ());
        dept.setCreateName (SecurityUtils.getUsername ());
        dept.setIsTop (1);
        return Result.success();
    }

    @Override
    public Result update(SupplierAddParam info) {
        sysCompanyMapper.updateByPrimaryKeySelective(info);

        bpoSupplierMapper.updateByPrimaryKeySelective(info.toBpoSupplier());

        return Result.success();
    }

    @Override
    public Result delete(Integer companyId) {

        //(假)删除供应商详情表
        bpoSupplierMapper.updateStatusByCompanyId(new BpoSupplier(companyId, DictDataConstants.DELETE_STATUS));

        //(假)删除供应商信息表(与项目关联关系)
        bpoSupplierInfoMapper.updateStatusByCompanyId(new BpoSupplierInfo(companyId, DictDataConstants.DELETE_STATUS));

        //删除与该供应商绑定的用户与部门
        sysDeptSubRelMapper.updateByInfo(new SysDeptSubRel(companyId,DictDataConstants.DEPT_SUB_REL_SUPPLIER,DictDataConstants.DELETE_STATUS));

        //(假)删除供应商数据(公司/组织)
        sysCompanyMapper.updateByPrimaryKeySelective(new SysCompany(companyId, DictDataConstants.DELETE_STATUS));

        return Result.success();
    }

    @Override
    public Result updateSupplierStatus(Integer companyId, Integer supplierStatus) {

        if(companyId != null && supplierStatus != null){
            return Result.requestParamError();
        }
        bpoSupplierMapper.updateSupplierStatusByCompanyId(companyId,supplierStatus);

        return Result.success();
    }

    @Override
    public Result ruleList(BpoSupplierRuleListShow info) {
        Result result = new Result(ResultConstants.REQUEST_SUCCESS);

        List<BpoSupplierRuleListShow> list = bpoSupplierInfoMapper.ruleList(info);

        result.setData(list);
        return result;
    }

    @Override
    public Result ruleDetail(BpoSupplierRuleDetailShow info) {
        Result result = new Result(ResultConstants.REQUEST_SUCCESS);

        BpoSupplierRuleDetailShow returnInfo = bpoSupplierInfoMapper.ruleDetail(info);

        result.setData(returnInfo);
        return result;
    }

    @Override
    public Result ruleDetailTime(BpoSupplierRuleDetailShow info) {
        Result result = new Result(ResultConstants.REQUEST_SUCCESS);

        BpoSupplierRuleDetailShow returnInfo = bpoSupplierInfoMapper.ruleDetailTime(info);

        result.setData(returnInfo);
        return result;
    }

    @Override
    public Result getInfoForAddFeeRule(Integer id) {
        if(id == null)
            return Result.requestParamError();

        Result result = new Result(ResultConstants.REQUEST_SUCCESS);

        List<BpoFeeRuleModelShow> list = bpoSupplierInfoMapper.getInfoForAddFeeRule(id);

        result.setData(list);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result addFeeRule(List<BpoFeeRuleParam> list) {
        Result result = new Result(ResultConstants.REQUEST_SUCCESS);
        for(BpoFeeRuleParam info:list) {
            if (info.getComputationalType() == 1) {
                //计费规则表(计件)新增 并获取Id
                BpoFeeRule bpoFeeRule = info.toBpoFeeRule();
                //规则表Id
                int ruleId = bpoFeeRuleMapper.insertSelective(bpoFeeRule);
                for (BpoProcessParam bpoProcessParam : info.getBpoProcessParams()) {
                    //工序Id
                    int sysProcessId = bpoProcessParam.getSysProcessId();
                    for (BpoProductParam bpoProductParam : bpoProcessParam.getBpoProductParams()) {
                        BpoProductPrice temp = bpoProductParam.toBpoProductPrice();
                        temp.setProcessId(sysProcessId);
                        temp.setFeeRuleId(ruleId);
                        //新增规则中间表数据
                        bpoProductPriceMapper.insertSelective(temp);
                    }
                }

            } else if (info.getComputationalType() == 2) {
                //计费规则表(计时)新增
                BpoFeeRuleByTime bpoFeeRuleByTime = info.toBpoFeeRuleByTime();
                bpoFeeRuleByTimeMapper.insertSelective(bpoFeeRuleByTime);
            }
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result deleteProductPrice(int[] id) {
        //接口复用
        return projectService.deleteProductPrice(id);
    }

    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT)
    @Override
    public Result findAllInDepByLoginUserId(BaseEntity param) {
        List<SysCompany> allTransByUserId = bpoSupplierInfoMapper.findAllByLoginUserId(param);
        Result<List<SysCompany>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allTransByUserId);
        return result;
    }

    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD)
    @Override
    public Result findAllByLoginUserId(BaseEntity param) {
        List<SysCompany> allTransByUserId = bpoSupplierInfoMapper.findAllByLoginUserId(param);
        Result<List<SysCompany>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allTransByUserId);
        return result;
    }

    @Override
    public Result getSupplierListByPorjectId(Integer projectId) {
        Result  result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        List<BpoSupplierSimpleList> list = bpoSupplierInfoMapper.getSupplierListByPorjectId(projectId);

        result.setData(list);
        return result;
    }

    @Override
    public List<BpoProjectSupplierListShow> selectByProjectId(Integer projectId) {
        return bpoSupplierInfoMapper.selectByProjectId(projectId);
    }

    @Override
    public BpoProjectSupplierListShow selectByPersonId(Integer personId) {
        return bpoSupplierInfoMapper.selectByPersonId(personId);
    }

}