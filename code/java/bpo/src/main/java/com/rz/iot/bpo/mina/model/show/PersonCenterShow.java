package com.rz.iot.bpo.mina.model.show;

import lombok.Data;

/**
 * @program: bpo-preview
 * @description: 个人中心展示
 * @author: YangShun
 * @create: 2020-07-22 17:29
 **/
@Data
public class PersonCenterShow {
    //驻场管理员
    private String transferManeger;
    //驻场管理员联系方式
    private String LinkPhone;
}
