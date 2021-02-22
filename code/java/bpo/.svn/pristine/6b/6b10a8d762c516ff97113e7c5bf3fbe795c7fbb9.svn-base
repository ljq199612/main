package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoCustomerInfos;
import com.rz.iot.bpo.model.SysCompany;

import java.util.List;

public interface BpoCustomerInfosService {
    Result<BpoCustomerInfos> getDetail(BpoCustomerInfos infos);

    Result insertCustomer(BpoCustomerInfos infos);

    Result updateCustomer(BpoCustomerInfos infos);

    Result deleteCustomer(Integer id);

    Result<Page<BpoCustomerInfos>> selectCustomergByCondition(BaseEntity entity,BpoCustomerInfos infos,Page<BpoCustomerInfos> page);

    Result findAllInDepByLoginUserId(BaseEntity param);

    Result findAllByLoginUserId(BaseEntity param);
}
