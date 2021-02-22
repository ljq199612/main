package com.rz.iot.bpo.model.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.iot.bpo.model.BpoPerformanceLevel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 描述 : 绩效考核等级参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BpoPerformanceLevelParam extends BpoPerformanceLevel{
    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间", example = "2020-06-06 16:04:32")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "修改时间", example = "2020-06-06 16:04:32")
    private Date updateTime;
}
