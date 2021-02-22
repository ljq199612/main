package com.rz.iot.bpo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.BpoCustomerInfosMapper;
import com.rz.iot.bpo.mapper.SysCompanyMapper;
import com.rz.iot.bpo.mapper.SysDeptMapper;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.service.BpoCustomerInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BpoCustomerInfosServiceImpl implements BpoCustomerInfosService {

    @Autowired(required = false)
    private BpoCustomerInfosMapper customerInfosMapper;

    @Autowired(required = false)
    private SysCompanyMapper sysCompanyMapper;

    @Autowired(required = false)
    private SysDeptMapper sysDeptMapper;

    /**
     * 根据客户详情列表id获取客户详情信息
     *
     * @param infos
     * @return
     */
    @Override
    public Result<BpoCustomerInfos> getDetail(BpoCustomerInfos infos) {
        infos.setUserId (getUserId ());
        BpoCustomerInfos byIdAndProjectID = customerInfosMapper.findByIdAndProjectID (infos);
        byIdAndProjectID.setDeptName (getName (byIdAndProjectID.getDept ()));
        Result<BpoCustomerInfos> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (byIdAndProjectID);
        return result;
    }

    /**
     * 新增一个客户
     *
     * @param infos
     * @return
     */
    @Override
    @Transactional
    public Result insertCustomer(BpoCustomerInfos infos) {
        Result result = addHandle (infos);
        if (result != null) return result;
        infos.setCreateTime (new Date ());
        infos.setUpdateTime (new Date ());
        SysCompany company = getSysCompany (infos, new SysCompany ());
        company.setCreateTime (new Date ());
        //插入信息进入公司表
        sysCompanyMapper.insertSelective (company);
        infos.setOrgId (company.getId ());
        customerInfosMapper.insertSelective (infos);
        SysDept dept = new SysDept ();
        dept.setDeptId (infos.getDeptId ());
        dept.setSubType (1);
        dept.setSubId (company.getId ());
        dept.setStatus (1);

        //插入信息进入关联表
        sysDeptMapper.insertCustomRelation (dept);
        return Result.success ();
    }

    /**
     * 更新一个客户
     *
     * @param infos
     * @return
     */
    @Override
    @Transactional
    public Result updateCustomer(BpoCustomerInfos infos) {
        Result result = updateHandle (infos);
        if (result != null) return result;
        infos.setUpdateTime (new Date ());
        SysCompany company = getSysCompany (infos, new SysCompany ());
        company.setId (infos.getOrgId ());
        BpoCustomerInfos bpoCustomerInfos = customerInfosMapper.selectByOrgId (infos.getOrgId ());
        infos.setId (bpoCustomerInfos.getId ());
        //更新客户表
        customerInfosMapper.updateByPrimaryKeySelective (infos);
        //更新公司表
        sysCompanyMapper.updateByPrimaryKeySelective (company);
        //更新关联表
        SysDept dept = new SysDept ();
        dept.setDeptId (infos.getDeptId ());
        dept.setSubType (DictDataConstants.DEPT_SUB_REL_CUSTOM);
        dept.setSubId (infos.getOrgId ());
        dept.setStatus (DictDataConstants.NORMAL_STATUS);

        sysDeptMapper.updateCustomRelation (dept);

        return Result.success ();
    }

    /**
     * 删除一个客户
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Result deleteCustomer(Integer id) {
        if (id == null) {
            return Result.requestParamError ("id为空");
        }
        List<BpoCustomerInfos> byIdAndProjectID = customerInfosMapper.findIsCanBeDelete (id);
        if (byIdAndProjectID.size () != 0) {
            return Result.requestParamError ("客户已经被关联项目，无法删除");
        }
        BpoCustomerInfos bpoCustomerInfos = customerInfosMapper.selectByPrimaryKey (id);
        bpoCustomerInfos.setStatus (String.valueOf (DictDataConstants.DELETE_STATUS));
        customerInfosMapper.updateByPrimaryKeySelective (bpoCustomerInfos);
        SysCompany sysCompany = new SysCompany ();
        sysCompany.setStatus (DictDataConstants.DELETE_STATUS);
        sysCompany.setId (bpoCustomerInfos.getOrgId ());
        sysCompanyMapper.updateByPrimaryKeySelective (sysCompany);
        sysDeptMapper.updateByCustomId (id);

        return Result.success ();
    }

    /**
     * 模糊查询 客户归属 客户类型 客户名称 场地名称 项目名称
     *
     * @param infos
     * @param page
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD)
    public Result<Page<BpoCustomerInfos>> selectCustomergByCondition(BaseEntity entity,BpoCustomerInfos infos, Page<BpoCustomerInfos> page) {
        PageHelper.startPage (page.getPageNum (), page.getPageSize ());
        Integer userId = getUserId ();
        infos.setUserId (userId);
        List<BpoCustomerInfos> bpoCustomerInfos = customerInfosMapper.CustomerByCondition (entity,infos);
        for (BpoCustomerInfos bpoCustomerInfo : bpoCustomerInfos) {
            bpoCustomerInfo.setDeptName (getName (bpoCustomerInfo.getDept ()  ));
            if(bpoCustomerInfo.getProject ()==null){
                bpoCustomerInfo.setProject (new BpoProject ());
            }
            if(bpoCustomerInfo.getTransferStation ()==null){
                bpoCustomerInfo.setTransferStation (new BpoTransferStation ());
            }
        }
        PageInfo<BpoCustomerInfos> pageInfo = new PageInfo<> (bpoCustomerInfos);
        page.setList (bpoCustomerInfos);
        page.setTotal (pageInfo.getTotal ());
        page.setPages (pageInfo.getPages ());

        Result<Page<BpoCustomerInfos>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (page);

        return result;
    }

    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT)
    @Override
    public Result findAllInDepByLoginUserId(BaseEntity param) {
        List<BpoCustomerInfos> allTransByUserId = customerInfosMapper.findAllByLoginUserId(param);
        Result<List<BpoCustomerInfos>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allTransByUserId);
        return result;
    }

    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD)
    @Override
    public Result findAllByLoginUserId(BaseEntity param) {
        List<BpoCustomerInfos> allTransByUserId = customerInfosMapper.findAllByLoginUserId(param);
        Result<List<BpoCustomerInfos>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allTransByUserId);
        return result;
    }


    //获取用户的user_id
    private Integer getUserId() {
        SysUser sysUser = SecurityUtils.getLoginUser ().getUser ();
        if (sysUser != null) {
            Integer user_Id = sysUser.getUserId ();
            return user_Id;
        }
        return null;
    }

    //将查询到的值对应录入到company
    private SysCompany getSysCompany(BpoCustomerInfos infos, SysCompany sysCompany) {
        if (infos != null && sysCompany != null) {
            sysCompany.setCompanyName (infos.getName ());
            sysCompany.setNickName (infos.getAbbreviations ());
            sysCompany.setContacts (infos.getLinkMan ());
            sysCompany.setPhone(infos.getLinkManPhone ());
            sysCompany.setCompanyCode (infos.getCode ());
            sysCompany.setOrgType (DictDataConstants.SYS_ORG_JF);
            sysCompany.setUpdateTime (new Date ());
            sysCompany.setStatus (DictDataConstants.NORMAL_STATUS);
        }
        return sysCompany;
    }

    //验证传入值是否为空
    private Result addHandle(BpoCustomerInfos infos) {
        if (infos.getName () == null) {
            return Result.requestParamError ("客户名称为空");
        }
        if (infos.getCode () == null) {
            return Result.requestParamError ("客户编码为空");
        }
        if (infos.getType () == null) {
            return Result.requestParamError ("客户类型为空");
        }
        if (infos.getLinkMan () == null) {
            return Result.requestParamError ("客户联系人为空");
        }
        if (infos.getLinkManPhone () == null) {
            return Result.requestParamError ("客户联系方式为空");
        }
        if (infos.getCusCompany () == null) {
            return Result.requestParamError ("签约公司为空");
        }
        if (infos.getCreditCode () == null) {
            return Result.requestParamError ("统一社会信用代码为空");
        }
        if (infos.getDeptId () == null) {
            return Result.requestParamError ("主体供应商归属部门为空");
        }
        if (StringUtils.isNotEmpty (infos.getLinkManPhone ()) && infos.getLinkManPhone ().length () != 11) {
            return Result.requestParamError ("手机号输入错误");
        }

        return null;
    }

    private Result updateHandle(BpoCustomerInfos infos) {
        if(infos.getOrgId ()==null){
            return Result.requestParamError ("公司id为空");
        }
        if (StringUtils.isNotEmpty (infos.getLinkManPhone ()) && infos.getLinkManPhone ().length () != 11) {
            return Result.requestParamError ("手机号输入错误");
        }
        return null;
    }

    //将组织进行拼接
    private String getName(List<SysDept> depts) {
        if(depts == null || depts.size () == 0){
            return null;
        }
        SysDept[] depts1 = new SysDept[depts.size ()];
        depts.toArray (depts1);
        StringBuffer back = new StringBuffer ();
        for (int i = 0; i <depts.size ()-1 ;i++) {
            for(int j=0 ; j<depts.size ()-i-1 ;j++){
                if(depts1[j].getDescendants ().length ()<depts1[j+1].getDescendants ().length ()) {
                    SysDept dept =depts1[j];
                    depts1[j] = depts1[j+1];
                    depts1[j+1] = dept;
                }
            }
        }
        for (int i = 0; i < depts1.length; i++) {
            if(i!=depts1.length-1){
                back.append (depts1[i].getDeptName ()+"/");
            }else {
                back.append (depts1[i].getDeptName ());
            }
        }
        return back.toString ();
    }

}

