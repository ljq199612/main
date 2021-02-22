package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoContractInvoiceInfo;
import com.rz.iot.bpo.model.BpoContractPaymentInfo;
import com.rz.iot.bpo.model.BpoContractSettlementInfo;
import com.rz.iot.bpo.model.BpoFileUpload;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BpoContractInfoParam {

    /**
     * 合同类型 1客户 2供应商
     */
    private Byte type;

    private String name;

    private String contractCode;

    private String customerCode;

    private Integer jiaOrgId;

    private Integer jiaDeptId;

    private String jiaOrgName;

    private String jiaUscc;

    private Integer yiOrgId;

    private String yiOrgName;

    private String yiUscc;

    private String cashDeposit;

    private Date signTime;

    private Date startTime;

    private Date endTime;

    private String filePath;

    private Byte status;

    private String remark;

    private List<BpoFileUpload> fileList;

    /**
     * 财务结算信息
     */
    private BpoContractSettlementInfo bpoContractSettlementInfo;

    /**
     * 财务付款信息
     */
    private List<BpoContractPaymentInfo> bpoContractPaymentInfoList;

    /**
     * 财务开票信息
     */
    private BpoContractInvoiceInfo bpoContractInvoiceInfo;
}
