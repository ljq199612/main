package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.param.BpoPublicParam;

import java.util.List;

public interface BpoPublicMapper {
    
    List<BpoPublicParam> getProject(BaseEntity entity);

    List<BpoPublicParam> getTransferStation(BaseEntity entity);

    List<BpoPublicParam> getCustomer(BaseEntity entity);

    List<BpoPublicParam> getSupplier(BaseEntity entity);

    List<BpoPublicParam> getPayer(BaseEntity entity);
}

