package com.rz.iot.bpo.mina.service;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.param.MinaAuditParam;
import com.rz.iot.bpo.mina.model.show.MinaPersonShow;
import com.rz.iot.bpo.model.BpoProject;

import java.util.List;

public interface MinaPersonAuditService {

    List<MinaPersonShow> findAll(BaseEntity entity,String select);

    Result<MinaPersonShow> getDetail(Integer id);

    Result getDept(BaseEntity entity);

    Result<List<BpoProject>> getProject(BaseEntity entity);

    Result check(MinaAuditParam param);
}
