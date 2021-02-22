package com.xxx.module.soa.service;

import com.xxx.module.soa.order.OrderDO;
import com.xxx.module.soa.order.OrderDTO;
import com.xxx.module.soa.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Override
    public OrderDTO getOrder(String sn, Long userId){
       return makeOrder(sn/*订单编号 */, userId);
    }


    /**
     *
     * @param sn  订单编号
     * @param userId 用户ID
     * @return OrderDTO
     */
    private OrderDTO makeOrder(String sn, Long userId){
        RestTemplate restTemplate = new RestTemplate();
        // 请求路径
        String userServiceURL = "http://localhost:8083/user/"+userId;
        UserVO userVO = restTemplate.getForObject(userServiceURL, UserVO.class);

        log.info("\n>>>>> 请求的服务地址：{}\n>>>>> 返回值： {}", userServiceURL, userVO);
        //保存订单
        OrderDTO orderDTO
                = OrderDTO.builder()
                .sn(sn)
                .userId(userVO.getId())
                .userName(userVO.getName())
                .build();

        // TODO 操作 OrderDO, 调用 DAO 层，写入数据库

        return orderDTO;
    }



}
