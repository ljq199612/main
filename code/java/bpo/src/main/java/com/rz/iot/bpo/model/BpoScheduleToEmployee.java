package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoScheduleToEmployee {
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

    //状态
    private Integer status;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}