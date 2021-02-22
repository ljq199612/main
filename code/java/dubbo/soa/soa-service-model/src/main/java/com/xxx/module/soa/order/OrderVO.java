package com.xxx.module.soa.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO {
    private Long id;
    // 订单编号
    private String sn;
    // 用户 ID
    private Long userId;

    private String userName;
    private Float  price;

}
