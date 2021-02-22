package com.rz.iot.bpo.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述 :创建项目添加项目 供应商的供应商
 * 作者 : wangluyao
 * 创建时间 : 2020/6/22 19:44
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SecondLevelSupplierParam {
    @ApiModelProperty(value = "数据id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "供应商的供应商id", example = "2")
    private Integer secondLevelSupplierId;
    @ApiModelProperty(value = "是否代发工资 1：是2：否", example = "2")
    private Integer secondLevelIsAgencySalary;
    @ApiModelProperty(value = "供应商的供应商名称", example = "我是一个供应商的供应商")
    private String secondLevelSupplierName;
    @ApiModelProperty(value = "供应商供应商合同id", example = "1")
    private Integer supplierToSupplierContractId;
    @ApiModelProperty(value = "供应商供应商合同名称", example = "1")
    private String supplierToSupplierContractName;
}
