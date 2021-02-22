package com.rz.iot.bpo.mina.model.show;

import com.rz.iot.bpo.mina.model.MinaPerson;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MinaEmployeeShow extends MinaPerson {
    // 班次信息
    MinaClassGenerateShow minaClassGenerateShow;

    // 人员列表
    List<MinaPerson> personList;

}