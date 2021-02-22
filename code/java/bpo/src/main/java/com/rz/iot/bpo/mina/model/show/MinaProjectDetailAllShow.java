package com.rz.iot.bpo.mina.model.show;

import com.rz.iot.bpo.model.show.BpoProjectDetailShow;
import com.rz.iot.bpo.model.show.BpoSupplierListRelatedShow;
import com.rz.iot.bpo.model.show.ProjectComputationalFeeRuleShow;
import com.rz.iot.bpo.model.show.ProjectConfigShow;
import lombok.Data;

/**
 * 描述 :
 * 作者 : wangluyao
 * 创建时间 : 2020/6/27 18:02
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class MinaProjectDetailAllShow {
    MinaProjectDetailShow minaProjectDetailShow;
    BpoSupplierListRelatedShow minaSupplierListRelatedShow;
    ProjectConfigShow projectConfigShow;
//    ProjectComputationalFeeRuleShow projectComputationalFeeRuleShow;
}
