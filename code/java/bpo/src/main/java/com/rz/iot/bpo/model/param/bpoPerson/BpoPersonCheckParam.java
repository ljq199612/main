package com.rz.iot.bpo.model.param.bpoPerson;

import lombok.Data;

/**
 * 描述 : 人员审核参数
 * 作者 : Rycony
 * 创建时间 : 2020/7/1 9:38
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoPersonCheckParam {
    // 人员名称
   private String personName;
   // 祖籍地
   private String idCardAddress;
   // 性别 1.男 2.女 0.未知
   private Integer sex;
   // 民族
   private String nation;
}
