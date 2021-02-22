package com.rz.iot.bpo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Field;
import java.util.Date;

@Data
public class BpoClasses {
    @ApiModelProperty(value = "id",hidden = true)
    private Integer id;
    //班次编号
    @ApiModelProperty(value = "班次编号", example = "SN_20200506_1600_2300")
    private String classSn;
    //班次名称
    @ApiModelProperty(value = "班次名称", example = "某某中转场早班")
    private String className;
    //开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "开始日期", example = "2020-05-06 ")
    private Date startDate;
    //结束日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "结束日期", example = "2020-05-13")
    private Date endDate;
    //开始时间
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间", example = "16:00:00")
    private Date startTime;
    //结束时间
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间", example = "23:00:00")
    private Date endTime;
    //到场时间
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "到场时间", example = "13:00:00")
    private Date arriveTime;
    //需求人数
    @ApiModelProperty(value = "需求人数", example = "30")
    private Integer peopleNumber;
    //项目id
    @ApiModelProperty(value = "项目id", example = "3")
    private Integer projectId;
    //来源1:接口录入2:文件导入3:手工输入
    @ApiModelProperty(value = "来源1:接口录入2:文件导入3:手工输入", example = "3")
    private Integer source;
    //排班状态1:已完成0:未完成
    @ApiModelProperty(value = "排班状态1:已完成0:未完成", example = "0")
    private Byte scheduleStatus;
    //批次id
    @ApiModelProperty(value = "批次id", hidden = true)
    private Integer batchId;
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;
    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateTime;
//    //批次id
//    @ApiModelProperty(value = "批次id", example = "1")
//    private Integer batchId;
//    @ApiModelProperty(value = "状态", example = "1")
//    private Integer status;
//    @ApiModelProperty(value = "创建时间", example = "2020-06-06 16:04:32")
//    private Date createTime;
//    @ApiModelProperty(value = "修改时间", example = "2020-06-06 16:04:32")
//    private Date updateTime;

    public static void main(String[] args) {
        Class<BpoClasses> bpoProjectClass = BpoClasses.class;
        Field[] fields = bpoProjectClass.getDeclaredFields();
        String className = "bpoClasses";
        for (Field field : fields) {
            String name = field.getName();
            Class<?> type = field.getType();
            String s = name.substring(0,1).toUpperCase()+name.substring(1);
            switch (type.toString()) {
                case "class java.lang.Integer":
                    System.out.println("Integer " + name + " = "+className+".get" + s + "();\n" +
                            "        if (" + name + " == null){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");
                    break;
                case "class java.lang.String":
                    System.out.println("String " + name + " = "+className+".get" + s + "();\n" +
                            "        if (StringUtils.isEmpty(" + name + ")){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");
                    break;
                case "class java.util.Date":
                    System.out.println("Date " + name + " = "+className+".get" + s + "();\n" +
                            "        if (" + name + " == null){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");

                    break;
            }
        }
    }
}