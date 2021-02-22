package com.rz.iot.bpo.mina.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MinaAbnormal {

    private Integer id;

    @NotNull(message = "未选择场地")
    private Integer transfrerStationId;
    @NotNull(message = "未选择所属供应商")
    private Integer supplierId;
    @NotNull(message = "未选择项目")
    private Integer projectId;
    @NotNull(message = "未选择异常类型")
    private Integer abnormalTpye;
    @NotBlank(message = "未选择异常级别")
    private String abnormalLevel;
    @NotBlank(message = "未填写描述")
    private String describe;
    @NotBlank(message = "未选择产生时间")
    private Date generationTime;

    private String deduction;

    private String punishMoney;

    private String isPerson;

    private String personMoney;

    private String personId;

    private String remark;


}