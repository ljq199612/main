package com.rz.iot.bpo.mina.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.BpoSupplierInfoMapper;
import com.rz.iot.bpo.mina.mapper.MinaSupplierMapper;
import com.rz.iot.bpo.mina.model.show.MinaSupplierDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaSupplierListShow;
import com.rz.iot.bpo.mina.service.MinaSupplierService;
import com.rz.iot.bpo.model.show.BpoProjectListShow;
import com.rz.iot.bpo.model.show.BpoSupplierSimpleList;
import com.rz.iot.bpo.model.show.SupplierDetailShow;
import com.rz.iot.bpo.model.show.SupplierListShow;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import com.rz.iot.bpo.service.SysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MinaSupplierServiceImpl implements MinaSupplierService {

    @Resource
    MinaSupplierMapper minaSupplierMapper;

    @Resource
    SysDeptService sysDeptService;

    @Resource
    BpoSupplierInfoMapper bpoSupplierInfoMapper;

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT)
    public Result list(BaseEntity param, MinaSupplierListShow info, Page<MinaSupplierListShow> page) {

        Result result = new Result(ResultConstants.REQUEST_SUCCESS);


        PageHelper.startPage(page.getPageNum(),page.getPageSize());

        List<MinaSupplierListShow> list = minaSupplierMapper.list(info,param);

        for (MinaSupplierListShow temp:list){
            if(temp.getBpoSupplier()!=null && temp.getBpoSupplier().getSupplierDeptId()!=null){
                SysDeptShow sysDeptShow = sysDeptService.getDeptAll(temp.getBpoSupplier().getSupplierDeptId());
                temp.setDeptInfo(sysDeptShow);
            }
        }

        PageInfo<MinaSupplierListShow> pageInfo = new PageInfo<>(list);

        page.setList(list);
        page.setTotal (pageInfo.getTotal ());
        page.setPages (pageInfo.getPages ());

        result.setData(page);
        return result;
    }

    @Override
    public Result<SupplierDetailShow> detail(Integer id) {
        Result<SupplierDetailShow> result = new Result(ResultConstants.REQUEST_SUCCESS);

        SupplierDetailShow info = minaSupplierMapper.detail(id);

        //填充部门信息
        if(info != null && info.getSupplierDeptId() != null){
            SysDeptShow sysDeptShow = sysDeptService.getDeptAll(info.getSupplierDeptId());
            info.setDeptInfo(sysDeptShow);
        }

        return Result.success(info);
    }

}
