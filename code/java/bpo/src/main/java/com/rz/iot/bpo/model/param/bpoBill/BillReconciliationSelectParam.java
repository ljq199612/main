package com.rz.iot.bpo.model.param.bpoBill;

import lombok.Data;

@Data
public class BillReconciliationSelectParam {

    //日期
    private String date;

    //收款方公司id
    private Integer payerId;

    //付款方公司id
    private Integer payeeId;

    //状态 2对账中 3已结清
    private Integer status;

    //类型 1应收款 2应付款
    private Integer type;
}
