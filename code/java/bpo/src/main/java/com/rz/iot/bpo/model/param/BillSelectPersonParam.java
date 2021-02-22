package com.rz.iot.bpo.model.param;

import lombok.Data;

@Data
public class BillSelectPersonParam {

    private String date;

    private Integer projectId;

    private Integer transId;

    private Integer companyId;
}
