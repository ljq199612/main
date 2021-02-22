package com.rz.iot.bpo.mina.model.param;

import lombok.Data;

/**
 * @program: bpo-preview
 * @description: 用于登录，修改密码的数据存储
 * @author: YangShun
 * @create: 2020-07-24 10:36
 **/
@Data
public class MinaLoginParm {
    //用户id
    private Integer userId;
    //用户名
    private String userName;
    //新密码
    private String password;
    //旧密码
    private String oldPassword;
}
