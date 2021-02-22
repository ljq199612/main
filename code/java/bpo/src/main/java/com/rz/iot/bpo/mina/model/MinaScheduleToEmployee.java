package com.rz.iot.bpo.mina.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 排班到个人
 * @Author: Liu Jiaqi
 * @Create: 2020-07-29
 */
@Data
public class MinaScheduleToEmployee {
    //
    private Integer id;

    //班次id
    private Integer scheduleId;

    //员工id
    private Integer userId;

    //工序id
    private Integer processId;

    //产品id
    private Integer productId;

    //工作小组id
    private Integer groupId;

    //最近修改人
    private Integer updateUserId;

    //状态  1: 正常   9: 删除
    private Integer status;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}