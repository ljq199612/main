package com.rz.iot.bpo.model.show;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 工作小组
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoWorkGroupShow {
    @ApiModelProperty(value = "小组id", example = "3")
    private Integer groupId;
    @ApiModelProperty(value = "小组编码", example = "GGMK-ZZZY-ZC-1")
    private String groupCode;
    @ApiModelProperty(value = "小组名称", example = "装车1组")
    private String groupName;

    private List<BpoProcessShow> bpoProcessShowList;
}
