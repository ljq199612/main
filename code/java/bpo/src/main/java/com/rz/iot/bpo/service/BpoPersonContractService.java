package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoPersonContractInfo;
import com.rz.iot.bpo.model.param.BpoContractInfoParam;
import com.rz.iot.bpo.model.show.BpoPersonContractInfoShow;

public interface BpoPersonContractService {

    void insert(BpoPersonContractInfo personContractInfo);

    BpoPersonContractInfoShow getDetail(Integer jiaId,Integer yiId);

    void update(BpoPersonContractInfo personContractInfo);
}
