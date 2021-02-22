package com.rz.iot.bpo.mina.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Data
public class MinaClassesGenerate {
    // 班次id
    private Integer id;
    // 批次id
    private Integer batchId;
    // 状态
    private Integer status;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
    //班次编号
    private String classSn;
    //班次名称
    private String className;
    //开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;
    //结束日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    //开始时间
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time startTime;
    //结束时间
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time endTime;
    //到场时间
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time arriveTime;
    //剔除时间
    private Integer excludeHour;
    //需求人数
    private Integer peopleNumber;
    //项目id
    private Integer projectId;
    //来源  1:接口录入2:文件导入3:手工输入
    private Integer source;
    //排班状态  1:已完成0:未完成
    private Byte scheduleStatus;
    //1:按小时2:按工序
    private Integer type;
    //是否是特殊岗位
    private Integer isSpecial;
    //特殊岗位补贴
    private String specialAllowance;
    //餐补
    private String mealAllowance;
    //晚班补贴
    private String nightAllowance;
    //是否有补贴
    private Integer isAllowance;
    //最近修改人
    private Integer updateUserId;
    //最近修改人
    private Integer updateUserName;
    //班次类型  1:工作日 2:周末 3: 特殊日期
    private Integer classType;
    //备注
    private String contents;
    //是否有工作小组  0: 没有  1: 有
    private Integer isClassGroupRel;
}