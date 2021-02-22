package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoOutputDetail {

    private Integer id;

    private Integer outputId;

    private Integer workModelId;

    private Integer workGroupId;

    private Integer processId;

    private Integer productId;

    private Integer productPriceId;

    private Integer output;

    private Date createTime;

    private Date updateTime;

}