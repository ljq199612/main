package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoFeeRule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 计价规则工序
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoFeeRuleProcessShow {
    @ApiModelProperty(value = "工序id", example = "2")
    private Integer processId;
    @ApiModelProperty(value = "工序编码", example = "GGMK-ZZZY-ZC")
    private String processCode;
    @ApiModelProperty(value = "工序名称", example = "装车")
    private String processName;

    private List<BpoFeeRulePriceShow> feeRulePriceShowList;

}
