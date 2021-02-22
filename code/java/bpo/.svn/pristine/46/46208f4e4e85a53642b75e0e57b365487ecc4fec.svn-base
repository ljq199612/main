package com.rz.iot.bpo.mina.model.show;

import com.rz.iot.bpo.model.BpoSupplier;
import com.rz.iot.bpo.model.SysCompany;
import com.rz.iot.bpo.model.SysCompanyFile;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 描述 : 供应商列表显示字段
 * 作者 : qin xian
 * 创建时间 : 2020/6/20 0020 18:18
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class MinaSupplierListShow extends SysCompany {

    @ApiModelProperty(value = "项目Id", example = "1")
    private Integer projectId;

    @ApiModelProperty(value = "项目名称", example = "项目A")
    private String projectName;

    @ApiModelProperty(value = "计价方式", example = "按小时")
    private Integer feeRuleType;

    @ApiModelProperty(value = "合同开始时间", example = "2020-7-25")
    private Date contractStartTime;

    @ApiModelProperty(value = "合同结束时间", example = "2020-7-25")
    private Date contractEndTime;

    @ApiModelProperty(value = "中转场Id", example = "1")
    private Integer transferStationId;

    @ApiModelProperty(value = "中转场名称", example = "中转场名称")
    private String transferStationName;

    @ApiModelProperty(value = "供应商Info表Id", example = "1")
    private Integer supplierInfoId;

    @ApiModelProperty(value = "供应商详情信息", example = "供应商详情信息")
    private BpoSupplier bpoSupplier;

    @ApiModelProperty(value = "供应商营业执照", example = "供应商营业执照")
    private List<SysCompanyFile> sysCompanyFile;

    @ApiModelProperty(value = "供应商部门信息", example = "供应商部门信息")
    private SysDeptShow deptInfo;
}