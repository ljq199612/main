package com.rz.iot.bpo.model.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ContractInfoParam {

    /**
     * 合同类型
     */
    private Integer type;

    /**
     * 合同名称
     */
    private String name;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 部门
     */
    private Integer deptId;

    private List<Integer> deptIds;

}
