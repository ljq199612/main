package com.rz.iot.bpo.model.show.bpoPerson;

import lombok.Data;

/**
 * 描述 : 人员薪资信息
 * 作者 : Rycony
 * 创建时间 : 2020/6/29 17:18
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoWagePerson {
    // 账户id
    private Integer accountId;
    // 开户行
    private String bank;
    // 账户
    private String accountNumber;
    // 薪酬类型
    private Integer wageType;
    // 支付类型
    private Integer payType;
    // 薪酬
    private String wage;
    // 发薪渠道
    private Integer payChannels;
    // 餐补
    private String mealAllowance;
    // 晚班补
    private String nightAllowance;
    // 其他补助
    private String otherAllowance;
}
