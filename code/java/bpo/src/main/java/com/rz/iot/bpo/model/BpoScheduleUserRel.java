package com.rz.iot.bpo.model;

import java.util.Date;

public class BpoScheduleUserRel {
    private Integer id;

    private Integer proId;

    private Integer scheduleId;

    private Byte status;

    private Date 创建时间;

    private Date 修改时间;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date get创建时间() {
        return 创建时间;
    }

    public void set创建时间(Date 创建时间) {
        this.创建时间 = 创建时间;
    }

    public Date get修改时间() {
        return 修改时间;
    }

    public void set修改时间(Date 修改时间) {
        this.修改时间 = 修改时间;
    }
}