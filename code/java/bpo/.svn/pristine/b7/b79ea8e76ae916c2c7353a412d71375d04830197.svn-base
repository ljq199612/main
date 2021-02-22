package com.rz.iot.bpo.service;

import com.rz.iot.bpo.model.param.BpoOutputEmployeeParam;
import com.rz.iot.bpo.model.param.BpoOutputParam;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputDetailEmployeeShow;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputDetailShow;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputShow;

import java.util.List;
import java.util.Map;

public interface BpoOutputService {

    void add(BpoOutputParam outputParam);

    List<BpoOutputShow> getOutputList(Map<String,Object> params);

    List<BpoOutputDetailShow> getOutputDetail(Integer id);

    void toEmployeeOutput(List<BpoOutputEmployeeParam> outputEmployeeParamList);

    List<BpoOutputDetailEmployeeShow> getEmployeeDetailList(Map<String,Object> params);
}
