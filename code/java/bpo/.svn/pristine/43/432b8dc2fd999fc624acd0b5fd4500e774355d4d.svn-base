package com.rz.iot.bpo.mina.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.iot.bpo.model.BpoTransferStation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Field;
import java.util.Date;

@Data
public class MinaProject {
    @ApiModelProperty(value = "计价类型", example = "1")
    private Integer feeRuleType;
    @ApiModelProperty(value = "id")
    private Integer id;
    //项目名称
    @ApiModelProperty(value = "项目名称", example = "这是一个项目")
    private String projectName;
    //项目id
    @ApiModelProperty(value = "项目sn", example = "SF_JR_MMMS_000001")
    private String projectSn;
    //合同id
    @ApiModelProperty(value = "合同id", example = "6")
    private Integer contractId;
    //中转场id
    @ApiModelProperty(value = "中转场id", example = "9")
    private Integer transferStationId;
    //甲方id
    @ApiModelProperty(value = "甲方id", example = "8")
    private Integer firstParty;
    //甲方联系人
    @ApiModelProperty(value = "甲方联系人", example = "李四")
    private String firstPartyLink;
    @ApiModelProperty(value = "甲方公司名称", example = "这是甲方公司名称")
    private String firstCompanyName;
    //甲方联系电话
    @ApiModelProperty(value = "甲方联系电话", example = "13899999999")
    private String firstPartyMobile;
    //乙方id
    @ApiModelProperty(value = "乙方id", example = "5")
    private Integer secondParty;
    @ApiModelProperty(value = "乙方公司名称", example = "这是乙方公司")
    private String secondCompanyName;
    //乙方联系人
    @ApiModelProperty(value = "乙方联系人", example = "张三")
    private String secondPartyLink;
    //乙方联系方式
    @ApiModelProperty(value = "乙方联系方式", example = "13699999999")
    private String secondPartyMobile;
    @ApiModelProperty(value = "保证金", example = "1000.00")
    private String cashDeposit;
    //项目开始日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "项目开始日期", example = "2020-02-05")
    private Date startDate;
    //项目结束日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "项目结束日期", example = "2020-08-05")
    private Date endDate;
    //备注
    @ApiModelProperty(value = "备注", example = "这是一条备注")
    private String remark;
    //状态
    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;
    //上级组织
    @ApiModelProperty(value = "上级组织", example = "5")
    private Integer parentOrganization;
    //创建时间
    @ApiModelProperty(hidden = true)
    private Date createTime;
    //修改时间
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @ApiModelProperty(value = "储存中转场信息", example = "data")
    private BpoTransferStation bpoTransferStation;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "合同开始时间", example = "2020-02-05")
    private Date startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "合同结束时间", example = "2020-08-05")
    private Date endTime;

    public static void main(String[] args) {
        Class<MinaProject> bpoProjectClass = MinaProject.class;
        Field[] fields = bpoProjectClass.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            Class<?> type = field.getType();
            String s = name.substring(0,1).toUpperCase()+name.substring(1);
            switch (type.toString()) {
                case "class java.lang.Integer":
                    System.out.println("Integer " + name + " = bpoProject.get" + s + "();\n" +
                            "        if (" + name + " == null){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");
                    break;
                case "class java.lang.String":
                    System.out.println("String " + name + " = bpoProject.get" + s + "();\n" +
                            "        if (StringUtils.isEmpty(" + name + ")){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");
                    break;
                case "class java.util.Date":
                    System.out.println("Date " + name + " = bpoProject.get" + s + "();\n" +
                            "        if (" + name + " == null){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");

                    break;
            }
        }
    }

}
