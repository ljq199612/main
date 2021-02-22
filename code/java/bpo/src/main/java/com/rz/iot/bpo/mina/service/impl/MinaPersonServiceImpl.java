package com.rz.iot.bpo.mina.service.impl;

import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.mapper.MinaPersonMapper;
import com.rz.iot.bpo.mina.model.show.MinaPersonShow;
import com.rz.iot.bpo.mina.service.MinaPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: bpo-preview
 * @description: 人员信息
 * @author: YangShun
 * @create: 2020-07-20 15:02
 **/
@Service
public class MinaPersonServiceImpl implements MinaPersonService {
    @Autowired(required = false)
    private MinaPersonMapper personMapper;

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD , deptSubRelAlias = "dept")
    public List<MinaPersonShow> findAll(BaseEntity entity, String select) {
        List<MinaPersonShow> all = personMapper.findAll (entity,select);
        return all;
    }

    @Override
    public Result getDetail(Integer id) {
        MinaPersonShow detail = personMapper.getDetail (id);
        return Result.success (detail);
    }

    @Override
    public Result getPerson(Integer id) {
        MinaPersonShow person = personMapper.getPerson(id);
        return Result.success(person);
    }

    @Override
    public Result getHead(Integer id) {
        MinaPersonShow head = personMapper.getHead(id);
        return Result.success(head);
    }
}
