package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoProject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述 : 项目管理项目列表 返回前端
 * 作者 : wangluyao
 * 创建时间 : 2020/6/29 14:28
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoProjectDetailListShow  {

    private Integer projectId;
    private String projectName;
    private String companyNickName;
    private String companyName;


    //昨日总工时
    @ApiModelProperty(value = "昨日总工时", example = "6666")
    private Integer yesterdayTotalWorkHour;
    //昨日规划人数
    @ApiModelProperty(value = "昨日规划人数", example = "356")
    private Integer yesterdaySchedulePeopleNumber;
    //昨日实到人数
    @ApiModelProperty(value = "昨日实到人数", example = "350")
    private Integer yesterdayReallyPeopleNumber;
    //昨日考勤异常人数
    @ApiModelProperty(value = "昨日考勤异常人数", example = "16")
    private Integer yesterdayAbnormalPeopleNumber;
    //昨日考勤完成率
    @ApiModelProperty(value = "昨日考勤完成率", example = "89")
    private Integer yesterdayCompletionRate;

    //今日总班次
    @ApiModelProperty(value = "今日总班次", example = "5")
    private Integer todayTotalArrangementClass;
    //今日已排班次
    @ApiModelProperty(value = "今日已排班次", example = "5")
    private Integer todayArrangementClass;
    //今日总规划人数
    @ApiModelProperty(value = "今日总规划人数", example = "420")
    private Integer todayArrangementPeopleNumber;
    //今日已规划人数
    @ApiModelProperty(value = "今日已规划人数", example = "420")
    private Integer todayPlannedNumber;
    //    //今日考勤异常人数异常
//    @ApiModelProperty(value = "今日考勤异常人数异常" ,example = "6666")
//    private Integer todayAbnormalPeopleNumber;
    //今日人员规划完成率
    @ApiModelProperty(value = "今日人员规划完成率", example = "80")
    private Integer TodayPersonnelPlanningCompletionRate;


}
