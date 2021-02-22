package com.xxx.module.soa.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDO {
    private Long id;
    // 订单编号
    private String sn;
    // 用户 ID
    private Long userId;
}