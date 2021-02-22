package com.rz.iot.bpo.model.show.bpoPerson;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 描述 : 人员合同信息
 * 作者 : Rycony
 * 创建时间 : 2020/6/29 16:43
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoContractPerson {
    // 人员合同id
    private Integer contractId;
    // 人员合同类型
    private byte contractType;
    // 合同名称
    private String contractName;
    // 合同编号
    private String contractCode;
    // 甲方公司id
    private Integer jiaCompanyId;
    // 甲方公司名称
    private String jiaCompanyName;
    // 是否缴纳社保
    private Integer isSocialSecurity;
    // 合同期限
    private Integer contractLimit;
    // 合同开始时间
    private String startTime;
    // 合同结束时间
    private String endTime;
    // 备注
    private String remark;
    // 合同附件
    private List<Map<String,String>> contractFile;
}
