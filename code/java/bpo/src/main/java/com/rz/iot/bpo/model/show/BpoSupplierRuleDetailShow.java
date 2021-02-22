package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoFeeRule;
import com.rz.iot.bpo.model.BpoFeeRuleByTime;
import com.rz.iot.bpo.model.param.BpoFeeRuleParam;
import com.rz.iot.bpo.model.param.ProjectConfigParam;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 供应商计薪规则详情Show
 * 作者 : qin xian
 * 创建时间 : 2020/6/22 0022 20:01
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoSupplierRuleDetailShow {

    //供应商Id
    private Integer supplierId;

    //项目Id
    private Integer projectId;

    //项目计价类型 1:按件计价,2:按小时
    private Integer feeRuleType;

    //工序(-产品) - 计件计价规则
    private List<BpoFeeRuleShow> bpoFeeRuleShowList;
}