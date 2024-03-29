package com.rz.iot.bpo.model.param.bpoBill;

import lombok.Data;

/**
 * @program: bpo-preview
 * @description: 账单应收搜索参数
 * @author: YangShun
 * @create: 2020-07-29 11:38
 **/
@Data
public class BillSelectParam {

    //日期
    private String date;

    //项目id
    private Integer projectId;

    //中转场id
    private Integer transferStationId;

    //收款方公司id
    private Integer payerId;

    //付款方公司id
    private Integer payeeId;

    //类型 1应收款 2应付款
    private Integer type;
}
