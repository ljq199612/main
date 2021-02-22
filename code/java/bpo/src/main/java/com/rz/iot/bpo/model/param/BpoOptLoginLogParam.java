package com.rz.iot.bpo.model.param;

import lombok.Data;

/**
 * 描述 : 操作日志前端参数实体类
 * 作者 : Rycony
 * 创建时间 : 2020/6/19 16:27
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoOptLoginLogParam {
    // 开始时间
    private String startDate;
    // 结束时间
    private String endDate;
    // 用户类型
    private String type;

}
