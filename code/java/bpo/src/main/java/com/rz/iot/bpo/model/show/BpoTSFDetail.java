package com.rz.iot.bpo.model.show;

import lombok.Data;

import java.util.Date;

/**
 * 描述 : 场地详情展示
 * 作者 : Rycony
 * 创建时间 : 2020/6/25 11:54
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoTSFDetail {
    private Integer id;

    private String name;

    private String code;

    private Integer areaId;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private String cityName;

    private Integer districtId;

    private String districtName;

    private String regionName;

    private String businessName;

    private String type;

    private String linkMan;

    private String phone;

    private String remark;

    private Byte status;

    private String detailAddress;

    private Date createTime;

    private Date updateTime;
}
