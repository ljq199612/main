package com.rz.iot.bpo.mina.model.param;

import com.rz.iot.bpo.mina.model.MinaClassesFromExternal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class MinaUpdateClassesParam extends MinaClassesFromExternal {
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;
    //批次id
    @ApiModelProperty(value = "批次id", hidden = true)
    private Integer batchId;
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;
    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateTime;

}
