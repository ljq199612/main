package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoEvaluationResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 绩效考核结果
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoEvaluationResultShow extends BpoEvaluationResult {

    @ApiModelProperty(value = "项目名称", example = "项目A")
    private  String projectName;

    @ApiModelProperty(value = "项目编号", example = "项目A")
    private  String projectSn;

    @ApiModelProperty(value = "供应商名称",example = "供应商B")
    private String supplierName;

    @ApiModelProperty(value = "场地Id",example = "1")
    private Integer transferStationId;

    @ApiModelProperty(value = "场地名称",example = "场地C")
    private String transferStationName;

    @ApiModelProperty(value = "场地编号",example = "CD-fa2114-10")
    private String transferStationCode;

}
