package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.mina.model.MinaContractInfo;
import com.rz.iot.bpo.model.BpoContractInfo;
import com.rz.iot.bpo.model.SysCompany;
import com.rz.iot.bpo.model.SysCompanyFile;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 描述 : 供应商详情Show
 * 作者 : qin xian
 * 创建时间 : 2020/6/22 0022 9:37
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SupplierDetailShow extends SysCompany{

    @ApiModelProperty(value = "公司成立时间", example = "2020-7-20 12:00:00")
    private Date companyCreateTime;

    @ApiModelProperty(value = "公司规模", example = "GSGM-BIG(100-200)")
    private String companySize;

    @ApiModelProperty(value = "所属行业", example = "ZHAOMING")
    private String industry;

    @ApiModelProperty(value = "所属部门Id", example = "88")
    private Integer supplierDeptId;

    @ApiModelProperty(value = "甲方统一社会信用代码", example = "HY-ZM")
    private String jiaCreditCode;

    @ApiModelProperty(value = "乙方统一社会信用代码", example = "HY-ZM")
    private String yiCreditCode;

    @ApiModelProperty(value = "统一社会信用代码", example = "HY-ZM")
    private String creditCode;

    @ApiModelProperty(value = "联系地址", example = "HY-ZM")
    private String address;

    @ApiModelProperty(value = "备注描述", example = "HY-ZM")
    private String describe;

    @ApiModelProperty(value = "供应商状态 1:启用2停用", example = "9")
    private Integer supplierStatus;


    @ApiModelProperty(value = "文件路径", example = "file_path")
    private List<SysCompanyFile> filePath;

    @ApiModelProperty(value = "供应商部门信息", example = "供应商部门信息")
    private SysDeptShow deptInfo;

    List<MinaContractInfo> MinaContractInfoList;

}