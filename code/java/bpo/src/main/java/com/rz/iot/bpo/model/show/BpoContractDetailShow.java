package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoContractInvoiceInfo;
import com.rz.iot.bpo.model.BpoContractPaymentInfo;
import com.rz.iot.bpo.model.BpoContractSettlementInfo;
import com.rz.iot.bpo.model.BpoFileUpload;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BpoContractDetailShow {

    private Integer id;

    private Integer projectId;

    private String name;

    private String contractCode;

    private String customerCode;

    private Integer jiaOrgId;

    private String jiaName;

    private String jiaUscc;

    private String jiaOrgName;

    private Integer jiaDeptId;

    private String jiaDeptName;

    private String yiOrgName;

    private Integer yiOrgId;

//    private String yiName;

    private String yiUscc;

    private String cashDeposit;

    /**
     * 类型:1企业-企业,2员工-劳务,3员工-实习协议,4员工-灵活排遣
     */
    private Byte type;

    private Date signTime;

    private Date startTime;

    private Date endTime;

    private String filePath;

    private Byte status;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private List<BpoFileUpload> fileList;

    /**
     * 财务结算信息
     */
    private BpoContractSettlementInfo bpoContractSettlementInfo;

    /**
     * 财务付款信息列表
     */
    private List<BpoContractPaymentInfo> bpoContractPaymentInfoList;

    /**
     * 财务开票信息
     */
    private BpoContractInvoiceInfo bpoContractInvoiceInfo;
}
