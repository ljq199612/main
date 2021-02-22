package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoClassesFromExternal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class BpoUpdateClassesParam extends BpoClassesFromExternal{
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
