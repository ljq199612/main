package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoWorkGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目生成工作模块参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoWorkModelParam {
    @ApiModelProperty(value = "系统配置工作模块id", example = "1")
    private Integer sysModuleId;
    @ApiModelProperty(value = "系统配置工作模块名称", example = "中转作业")
    private String sysModuleName;

    @ApiModelProperty(value = "系统设置工作模块编号", example = "GGMK-ZZZY")
    private String sysModuleCode;

    private Boolean codeExist;
    private Boolean codeInvalid;
    private Boolean nameExist;

    //工作小组
    private List<BpoGroupParam> bpoGroupParamList;
}
