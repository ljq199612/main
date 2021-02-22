package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysDept;
import com.rz.iot.bpo.model.SysDictData;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;

import java.util.List;
import java.util.Map;

public interface SysDeptService {

    Result addDept(SysDept dept);

    Result updateDept(SysDept dept);

    Result deleteDept(Integer id);

    Result<List<SysDept>> getAllByUserId(BaseEntity entity);

    Result<List<SysDept>> findCanControl(BaseEntity entity);

    Result<Map<Integer, SysDept>> getCondition(BaseEntity entity,Integer status,String name);

    Result updateStatus(SysDept dept);

    Result<SysDept> getOneByDeptId(Integer id);

    Result<List<SysDictData>> findType();

    Result findDeptAll(Integer deptId);

    SysDeptShow getDeptAll(Integer deptId);
}
