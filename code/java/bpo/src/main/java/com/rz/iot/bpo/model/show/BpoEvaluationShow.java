package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoEvaluationItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目考核加扣分规则参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/26 14:37
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoEvaluationShow {
    @ApiModelProperty(value = "项目id", example = "3")
    private Integer projectId;
    @ApiModelProperty(value = "供应商信息表id", example = "3")
    private Integer supplierInfoId;

    private List<BpoEvaluationItem> bpoEvaluationItemList;

}
