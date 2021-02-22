package com.rz.iot.bpo.model;

import lombok.Data;

@Data
public class SysAreaRel {
    private Integer id;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private String cityName;

    private Integer districtId;

    private String districtName;

    private Integer status;
}