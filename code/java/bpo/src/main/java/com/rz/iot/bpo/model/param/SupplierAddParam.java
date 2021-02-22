package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoSupplier;
import com.rz.iot.bpo.model.SysCompany;
import com.rz.iot.bpo.model.SysCompanyFile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 描述 : 供应商新增Parma company表+supplier表
 * 作者 : qin xian
 * 创建时间 : 2020/6/22 0022 11:28
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SupplierAddParam extends SysCompany{



    @ApiModelProperty(value = "公司成立时间", example = "2020-07-22 12:00:00")
    private Date companyCreateTime;

    @ApiModelProperty(value = "公司规模 (存储业务字典key)", example = "GM-01")
    private String companySize;

    @ApiModelProperty(value = "所属行业(存储业务字典key)", example = "HY-ZM")
    private String industry;

    @ApiModelProperty(value = "归属部门Id", example = "HY-ZM")
    private Integer supplierDeptId;

    @ApiModelProperty(value = "统一社会信用代码", example = "HY-ZM")
    private String creditCode;

    @ApiModelProperty(value = "联系地址", example = "HY-ZM")
    private String address;

    @ApiModelProperty(value = "备注描述", example = "HY-ZM")
    private String describe;

    @ApiModelProperty(value = "供应商状态 1:启用2停用", example = "2")
    private Integer supplierStatus;

    List<SysCompanyFile> sysCompanyFiles;

    public BpoSupplier toBpoSupplier(){
        BpoSupplier info = new BpoSupplier();
        //新增供应商（公司/组织）后获得Id,正常为null
        info.setCompanyId(this.getId());
        info.setCompanyCreateTime(this.getCompanyCreateTime());
        info.setCompanySize(this.getCompanySize());
        info.setIndustry(this.getIndustry());
        info.setSupplierDeptId(this.getSupplierDeptId());
        info.setCreditCode(this.getCreditCode());
        info.setAddress(this.getAddress());
        info.setDescribe(this.getDescribe());
        info.setSupplierStatus(this.getSupplierStatus());

        return info;
    }

}