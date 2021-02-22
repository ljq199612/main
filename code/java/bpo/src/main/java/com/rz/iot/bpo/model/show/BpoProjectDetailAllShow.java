package com.rz.iot.bpo.model.show;

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
public class BpoProjectDetailAllShow {
    BpoProjectDetailShow bpoProjectDetailShow;
    BpoSupplierListRelatedShow bpoSupplierListRelatedShow;
    ProjectConfigShow projectConfigShow;
    ProjectComputationalFeeRuleShow projectComputationalFeeRuleShow;
}
