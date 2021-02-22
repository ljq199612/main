package com.xxx.module.soa.order;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO{

    private Long id;
    // 订单编号
    private String sn;
    // 用户 ID
    private Long userId;

    private String userName;
    private Float  price;


}