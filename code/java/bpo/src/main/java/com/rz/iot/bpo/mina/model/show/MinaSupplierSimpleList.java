package com.rz.iot.bpo.mina.model.show;

import lombok.Data;

/**
 * 描述 : 供应商信息列表(简易：项目信息+供应商信息)
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class MinaSupplierSimpleList {
    private Integer projectId;

    private String projectName;

    private Integer companyId;

    private String companyName;

    private Integer supplierInfoId;

    private Integer contractId;
}