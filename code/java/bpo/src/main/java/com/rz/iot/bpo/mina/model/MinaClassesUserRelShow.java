package com.rz.iot.bpo.mina.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 排班到个人的关联表
 * @Author: Liu Jiaqi
 * @Create: 2020-07-29
 */
@Data
public class MinaClassesUserRelShow extends MinaClassesUserRel{
    // 工作小组名称
    private String workGroupName;
}