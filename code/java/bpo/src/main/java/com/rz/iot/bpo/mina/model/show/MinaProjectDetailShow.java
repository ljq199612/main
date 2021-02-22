package com.rz.iot.bpo.mina.model.show;

import com.rz.iot.bpo.model.SysAreaRel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 描述 : 项目信息
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class MinaProjectDetailShow {
    //
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;
    //项目名称
    @ApiModelProperty(value = "项目名称", example = "这是一个项目")
    private String projectName;
    //项目id
    @ApiModelProperty(value = "项目id", example = "3")
    private String projectSn;
    //合同id
    @ApiModelProperty(value = "合同id", example = "6")
    private Integer contractId;
    //项目负责人
    private String projectLeader;
    //合同id
    @ApiModelProperty(value = "合同名称", example = "某某合同")
    private String contractName;
    //中转场id
    @ApiModelProperty(value = "中转场id", example = "9")
    private Integer transferStationId;
    //中转场id
    @ApiModelProperty(value = "中转场名字", example = "中转场名字")
    private String transferStationName;
    //中转场id
    @ApiModelProperty(value = "中转场id", example = "中转场名字")
    private Integer cityId;
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
    //甲方名称
    @ApiModelProperty(value = "甲方名称", example = "这是甲方名词")
    private String firstPartyName;
    //乙方id
    @ApiModelProperty(value = "乙方id", example = "5")
    private Integer secondParty;
    //乙方名字
    @ApiModelProperty(value = "乙方名字", example = "这是乙方名字")
    private String secondPartyName;
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

    @ApiModelProperty(value = "项目开始日期", example = "2020-02-05")
    private Date startDate;
    //项目结束日期
    @ApiModelProperty(value = "项目结束日期", example = "2020-02-05")
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
    private Date createTime;
    //修改时间
    private Date updateTime;
    @ApiModelProperty(value = "合同开始时间", example = "2020-02-05")
    private Date startTime;
    @ApiModelProperty(value = "合同结束时间", example = "2020-02-05")
    private Date endTime;

    //计价方式
    private Integer feeRuleType;

    private SysAreaRel sysAreaRel;




}