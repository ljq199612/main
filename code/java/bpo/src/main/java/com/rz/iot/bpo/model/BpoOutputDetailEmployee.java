package com.rz.iot.bpo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BpoOutputDetailEmployee {

    private Integer id;

    private Integer outputDetailId;

    private Integer personId;

    private Float totalProductiveHours;

    private BigDecimal price;

    private Integer output;

    private Date createTime;

    private Date updateTime;

}