package com.rz.iot.bpo.model;

import com.rz.iot.bpo.mapper.BpoEvaluationContentMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BpoEvaluationItem {
    private Integer id;
    //项目id
    @ApiModelProperty(value = "项目id",example = "3")
    private Integer projectId;
    //供应商信息表id
    @ApiModelProperty(value = "供应商信息表id",example = "1")
    private Integer supplierInfoId;
    //考核项目名称
    @ApiModelProperty(value = "考核项目名称",example = "质量管理")
    private String evaluationItemName;
    //权重
    @ApiModelProperty(value = "权重",example = "60")
    private Integer weight;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private List<BpoEvaluationContent> bpoEvaluationContentList;

}