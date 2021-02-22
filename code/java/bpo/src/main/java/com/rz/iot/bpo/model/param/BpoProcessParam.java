package com.rz.iot.bpo.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目生成工序参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoProcessParam {
    @ApiModelProperty(value = "系统配置工序id", example = "2")
    private Integer sysProcessId;
    @ApiModelProperty(value = "系统配置工序名称", example = "装车")
    private String sysProcessName;
    @ApiModelProperty(value = "系统配置工序编号", example = "GGMK-ZZZY-ZC")
    private String sysProcessCode;


    //    //工作小组id
//    private List<BpoGroupParam> bpoGroupParamList;
    private Boolean codeExist;
    private Boolean codeInvalid;
    private Boolean nameExist;
    //产品
    private List<BpoProductParam> bpoProductParams;


}
