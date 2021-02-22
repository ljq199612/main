package com.xxx.module.soa.service;

import com.xxx.module.soa.order.OrderDTO;

public interface OrderService {
    OrderDTO getOrder(String sn, Long userId);
}
