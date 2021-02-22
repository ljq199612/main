package com.rz.iot.bpo.model.show;

import lombok.Data;

@Data
public class BpoProjectSupplierListShow {

    private Integer supplierInfoId;

    private Integer parentId;

    private Integer isAgencySalary;

    private Integer companyId;

    private String companyName;
}
