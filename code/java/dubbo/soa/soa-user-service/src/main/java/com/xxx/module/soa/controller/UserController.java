package com.xxx.module.soa.controller;

import com.xxx.module.soa.service.UserService;
import com.xxx.module.soa.user.UserDTO;
import com.xxx.module.soa.user.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/test/{userName}")
    @ResponseBody
    public String test(@PathVariable("userName") String name){
        return  "hello, "+name;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public UserVO getUser(@PathVariable("id") Long id){
        UserDTO userDTO = userService.getUse(id);

        return UserVO.builder()
                .id(userDTO.getId())
                .idCard(userDTO.getIdCard())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .build();
    }

}
