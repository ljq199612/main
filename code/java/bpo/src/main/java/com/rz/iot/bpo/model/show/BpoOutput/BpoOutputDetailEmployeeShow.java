package com.rz.iot.bpo.model.show.bpoOutput;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BpoOutputDetailEmployeeShow {

    private Integer id;

    private String personName;

    private String workNo;

    private Double totalProductiveHours;

    private String workModelName;

    private String workGroupName;

    private String processName;

    private String productName;

    private Integer lowerLimit;

    private Integer upperLimit;

    private BigDecimal price;

    private Integer output;

}
