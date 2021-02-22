package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoClassesGenerate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 描述 : 排班参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BpoClassesGenerateParam extends BpoClassesGenerate {
    @ApiModelProperty(value = "id", example = "1", hidden = true, allowEmptyValue = true)
    private Integer id;
    @ApiModelProperty(value = "批次id", example = "1", hidden = true, allowEmptyValue = true)
    private Integer batchId;
    @ApiModelProperty(value = "状态", example = "1", hidden = true, allowEmptyValue = true)
    private Integer status;
    @ApiModelProperty(value = "创建时间", example = "1", hidden = true, allowEmptyValue = true)
    private Date createTime;
    @ApiModelProperty(value = "修改时间", example = "1", hidden = true, allowEmptyValue = true)
    private Date updateTime;
}
