package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.BpoPublicMapper;
import com.rz.iot.bpo.model.param.BpoPublicParam;
import com.rz.iot.bpo.service.BpoPublicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: bpo-preview
 * @description: 公共服务层，用于获取场地，项目，供应商，客户
 * @author: YangShun
 * @create: 2020-07-29 11:42
 **/
@Service
public class BpoPublicServiceImpl implements BpoPublicService {

    @Resource
    private BpoPublicMapper publicMapper;

    /**
     * 用于获取当前用户部门的项目信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT,deptSubRelAlias = "rel")
    public Result<List<BpoPublicParam>> getProject(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getProject (entity));
        return result;
    }

    /**
     * 用于获取当前用户部门的场地信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT,deptSubRelAlias = "rel")
    public Result<List<BpoPublicParam>> getTransferStation(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getTransferStation (entity));
        return result;
    }

    /**
     * 用于获取当前用户部门的客户信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT,deptSubRelAlias = "rel")
    public Result<List<BpoPublicParam>> getCustomer(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getCustomer (entity));
        return result;
    }

    /**
     * 用于获取当前用户部门的供应商信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT)
    public Result<List<BpoPublicParam>> getSupplier(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getSupplier (entity));
        return result;
    }

    /**
     * 用于获取当前用户部门以及以下的项目信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "rel")
    public Result<List<BpoPublicParam>> getProjectDown(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getProject (entity));
        return result;
    }

    /**
     * 用于获取当前用户部门以及以下的中转场信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "rel")
    public Result<List<BpoPublicParam>> getTransferStationDown(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getTransferStation (entity));
        return result;
    }

    /**
     * 用于获取当前用户部门以及以下的客户信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "rel")
    public Result<List<BpoPublicParam>> getCustomerDown(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getCustomer (entity));
        return result;
    }

    /**
     * 用于获取当前用户部门以及以下的供应商信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD)
    public Result<List<BpoPublicParam>> getSupplierDown(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getSupplier (entity));
        return result;
    }

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT)
    public Result<List<BpoPublicParam>> getPayer(BaseEntity entity) {
        Result<List<BpoPublicParam>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (publicMapper.getPayer (entity));
        return result;
    }
}
