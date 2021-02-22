package com.rz.iot.bpo.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 创建项目添加项目 乙方的供应商
 * 作者 : wangluyao
 * 创建时间 : 2020/6/22 19:43
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class FirstLevelSupplierParam {
    @ApiModelProperty(value = "数据id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "乙方的供应商id", example = "1")
    private Integer firstLevelSupplierId;
    @ApiModelProperty(value = "是否代发薪资(1:是2:否)", example = "1")
    private Integer firstLevelIsAgencySalary;
    @ApiModelProperty(value = "乙方的供应商名称", example = "我是一个乙方的供应商")
    private String firstLevelSupplierName;
    @ApiModelProperty(value = "乙方供应商合同id", example = "1")
    private Integer partyBToSupplierContractId;
    @ApiModelProperty(value = "乙方供应商合同名称", example = "1")
    private String partyBToSupplierContractName;

    List<SecondLevelSupplierParam> secondLevelSupplierParamList;
}
