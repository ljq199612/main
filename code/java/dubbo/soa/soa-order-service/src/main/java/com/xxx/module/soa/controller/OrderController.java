package com.xxx.module.soa.controller;

import com.xxx.module.soa.order.OrderDTO;
import com.xxx.module.soa.order.OrderVO;
import com.xxx.module.soa.service.OrderService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private OrderService orderService;

    @ResponseBody
    @GetMapping("/test/{userId}/{orderSN}")
    public OrderDTO createOrder(@PathVariable("orderSN") String sn,  @PathVariable("userId") Long userId){
        OrderDTO orderDTO = orderService.getOrder(sn, userId);
        // TODO 该返回 VO和视图，这里偷懒了
        return orderDTO;
    }

    @ResponseBody
    @GetMapping("/test2")
    public List<String> getServiceList(){
        // 拿到所有的服务名称
        return discoveryClient.getServices();
    }

    @ResponseBody
    @GetMapping("/test3/{service}")
    public List<ServiceInstance> getService(@PathVariable("service")  @DefaultValue("user-Service") String service){
        // 拿到指定的服务
        return discoveryClient.getInstances(service);
    }





}
