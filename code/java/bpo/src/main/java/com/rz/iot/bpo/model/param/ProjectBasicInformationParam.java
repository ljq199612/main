package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoProject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述 : 项目生成项目参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectBasicInformationParam extends BpoProject {
    //合同id
    @ApiModelProperty(value = "合同名称", example = "这是一个合同")
    private String contractName;
    @ApiModelProperty(value = "中转场名字", example = "这是一个中转场")
    private String transferStationName;

}