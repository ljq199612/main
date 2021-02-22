package com.rz.iot.bpo.mina.service.impl;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.mapper.MinaAbnormalMapper;
import com.rz.iot.bpo.mina.model.MinaAbnormal;
import com.rz.iot.bpo.mina.model.show.MinaAbnormalShow;
import com.rz.iot.bpo.mina.service.MinaAbnormalService;
import com.rz.iot.bpo.model.BpoPerson;
import com.rz.iot.bpo.model.BpoProject;
import com.rz.iot.bpo.model.BpoTransferStation;
import com.rz.iot.bpo.model.SysCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: bpo-preview
 * @description: 异常上报
 * @author: YangShun
 * @create: 2020-07-22 11:16
 **/
@Service
public class MinaAbnormalServiceImpl implements MinaAbnormalService {

    @Autowired(required = false)
    private MinaAbnormalMapper abnormalMapper;

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "dept")
    public List<MinaAbnormalShow> findAll(BaseEntity entity,String select) {
        List<MinaAbnormalShow> all = abnormalMapper.findAll (select,entity);
        return all;
    }

    @Override
    public Result<MinaAbnormalShow> getDetail(Integer id) {
        Result<MinaAbnormalShow> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (abnormalMapper.getDetail (id));
        return result;
    }

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "rel")
    public Result<List<BpoProject>> getProject(BaseEntity entity) {
        Result<List<BpoProject>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (abnormalMapper.getProject (entity));
        return result;
    }

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "sub_rel")
    public Result<List<BpoTransferStation>> getTransfer(BaseEntity entity) {
        Result<List<BpoTransferStation>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (abnormalMapper.getTransfer (entity));
        return result;
    }

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD)
    public Result<List<SysCompany>> getSupplier(BaseEntity entity) {
        Result<List<SysCompany>>  result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (abnormalMapper.getSupplier (entity));
        return result;
    }

    @Override
    public Result insertAbnormal(MinaAbnormal abnormal) {
        abnormalMapper.insertSelective (abnormal);
        return Result.success ();
    }

    @Override
    public Result<List<BpoPerson>> getPerson(Integer projectId) {
        Result<List<BpoPerson>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (abnormalMapper.getPerson (projectId));
        return result;
    }


}
