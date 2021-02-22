package com.rz.iot.bpo.model.show;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述 :
 * 作者 : wangluyao
 * 创建时间 : 2020/6/24 10:29
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class NameOrCodeExistsShow {
    @ApiModelProperty(value = "名字是否存在",example = "false")
    private Boolean isNameExists;
    @ApiModelProperty(value = "编码是否存在",example = "false")
    private Boolean isCodeExists;


}
