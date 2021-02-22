package com.rz.iot.bpo.mina.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.controller.ProjectController;
import com.rz.iot.bpo.framework.security.LoginUser;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.BpoProjectMapper;
import com.rz.iot.bpo.mapper.BpoSupplierInfoMapper;
import com.rz.iot.bpo.mapper.SysAreaRelMapper;
import com.rz.iot.bpo.mapper.SysCompanyMapper;
import com.rz.iot.bpo.mina.mapper.MinaProjectMapper;
import com.rz.iot.bpo.mina.mapper.MinaSupplierMapper;
import com.rz.iot.bpo.mina.model.show.MinaProjectDetailAllShow;
import com.rz.iot.bpo.mina.model.show.MinaProjectDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaProjectListShow;
import com.rz.iot.bpo.mina.service.MinaProjectService;
import com.rz.iot.bpo.mina.service.MinaSupplierService;
import com.rz.iot.bpo.model.SysAreaRel;
import com.rz.iot.bpo.model.SysCompany;
import com.rz.iot.bpo.model.show.*;
import com.rz.iot.bpo.service.SupplierService;
import com.rz.iot.bpo.service.SysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

import static com.rz.iot.bpo.common.constant.ResultConstants.REQUEST_SUCCESS;

@Service
public class MinaProjectServiceImpl implements MinaProjectService {

    @Resource
    MinaProjectMapper minaProjectMapper;

    @Resource
    SysDeptService sysDeptService;

    @Resource
    SysAreaRelMapper sysAreaRelMapper;

    @Resource
    BpoSupplierInfoMapper bpoSupplierInfoMapper;

    @Resource
    SysCompanyMapper sysCompanyMapper;

    @Resource
    BpoProjectMapper projectMapper;

    @Resource
    MinaSupplierMapper minaSupplierMapper;

    @Override
    public Result getList(MinaProjectListShow param,Page<MinaProjectListShow> page) {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Integer userId = loginUser.getUser().getUserId();

        PageHelper.startPage (page.getPageNum (),page.getPageSize ());

        List<MinaProjectListShow> list =  minaProjectMapper.getList(param,userId);
        //添加部门信息
        for(MinaProjectListShow info:list){
            if(info.getDeptId()!=null)
                //通过部门Id获取部门层级
                info.setSysDeptShow(sysDeptService.getDeptAll(info.getDeptId()));
        }

        PageInfo<MinaProjectListShow> pageInfo = new PageInfo<> (list);
        page.setList (list);
        page.setTotal (pageInfo.getTotal ());
        page.setPages (pageInfo.getPages ());

         return Result.success(page);
    }

    @Override
    public Result<MinaProjectDetailShow> detail(int id) {
        MinaProjectDetailShow projectDetail = getBpoProjectDetailShow(id);

        Result<MinaProjectDetailShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(projectDetail);
        return result;
    }


    private Integer getCurrentUserCompanyId() {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        SysCompany companyByUserId = sysCompanyMapper.findCompanyByUserId(userId);
        return companyByUserId.getId();
    }

    @Override
    public Result<MinaProjectDetailAllShow> detailAll(int id) {
//        BpoProjectDetailAllShow bpoProjectDetailAllShow = new BpoProjectDetailAllShow();

        MinaProjectDetailAllShow minaProjectDetailAllShow = new MinaProjectDetailAllShow();

        //项目信息
        MinaProjectDetailShow projectDetail = getBpoProjectDetailShow(id);
        Integer companyId = getCurrentUserCompanyId();
        //项目关联供应商列表

        BpoSupplierListRelatedShow bpoSupplierListRelatedShow = minaSupplierMapper.findSupplierByProjectId(id,companyId);

        //项目配置 工作模块-工序-工作小组

        ProjectConfigShow projectConfig = minaProjectMapper.getProjectConfig(id);

        //数据拼接
        minaProjectDetailAllShow.setMinaProjectDetailShow(projectDetail);
        minaProjectDetailAllShow.setMinaSupplierListRelatedShow(bpoSupplierListRelatedShow);
        minaProjectDetailAllShow.setProjectConfigShow(projectConfig);

        Result<MinaProjectDetailAllShow> result = new Result<>(REQUEST_SUCCESS);
        result.setData(minaProjectDetailAllShow);
        return result;
    }

    private MinaProjectDetailShow getBpoProjectDetailShow(int id) {
        MinaProjectDetailShow projectDetail = minaProjectMapper.findProjectDetail(id);

        Integer cityId = projectDetail.getCityId();
        SysAreaRel sysAreaRel = sysAreaRelMapper.selectByPrimaryKey(cityId);
        projectDetail.setSysAreaRel(sysAreaRel);
        return projectDetail;
    }
}
