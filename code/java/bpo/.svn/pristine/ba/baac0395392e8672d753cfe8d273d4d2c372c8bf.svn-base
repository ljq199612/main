package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.BpoOutputEmployeeParam;
import com.rz.iot.bpo.model.param.BpoOutputParam;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputDetailEmployeeShow;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputDetailShow;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputShow;
import com.rz.iot.bpo.model.show.ProjectConfigListShow;
import com.rz.iot.bpo.service.BpoOutputService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class BpoOutputServiceImpl implements BpoOutputService {

    @Resource
    private BpoOutputMapper outputMapper;

    @Resource
    private BpoOutputDetailMapper outputDetailMapper;

    @Resource
    private BpoBillPieceMapper bpoBillPieceMapper;

    @Resource
    private BpoOutputDetailEmployeeMapper outputDetailEmployeeMapper;

    @Resource
    private BpoProductMapper bpoProductMapper;

    @Resource
    private BpoProductPriceMapper productPriceMapper;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void add(BpoOutputParam outputParam) {
        int outputId = addOutput(outputParam);
        addOutputDetail(outputParam,outputId);
    }

    @Override
    public List<BpoOutputShow> getOutputList(Map<String, Object> params) {
        List<BpoOutputShow> outputShow = outputMapper.getOutputList(params);
        return outputShow;
    }

    @Override
    public List<BpoOutputDetailShow> getOutputDetail(Integer id) {
        List<BpoOutputDetailShow> outputDetailShow = outputDetailMapper.selectDetailByOutputId(id);
        return outputDetailShow;
    }

    @Override
    public void toEmployeeOutput(List<BpoOutputEmployeeParam> outputEmployeeParamList) {
        for (BpoOutputEmployeeParam param : outputEmployeeParamList) {
            for (BpoOutputDetailEmployee outputDetailEmployee: param.getOutputDetailEmployeeList()) {
                BpoOutputDetailEmployee record = new BpoOutputDetailEmployee();
                record.setOutputDetailId(param.getOutputDetailId());
                record.setPersonId(outputDetailEmployee.getPersonId());
                record.setOutput(outputDetailEmployee.getOutput());
                record.setPrice(outputDetailEmployee.getPrice());
                record.setTotalProductiveHours(outputDetailEmployee.getTotalProductiveHours());
                outputDetailEmployeeMapper.insertSelective(record);

                BpoOutputDetail bpoOutputDetail = outputDetailMapper.selectByPrimaryKey (param.getOutputDetailId ());
                BpoOutput bpoOutput = outputMapper.selectByPrimaryKey (bpoOutputDetail.getOutputId ());
                createBillPiece (bpoOutputDetail,record,bpoOutput);
            }
        }
    }

    private void createBillPiece(BpoOutputDetail outputDetail,BpoOutputDetailEmployee employee,BpoOutput output){
        DateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        BpoBillPiece bpoBillPiece = new BpoBillPiece ();
        bpoBillPiece.setWorkModelId (outputDetail.getWorkModelId ());
        bpoBillPiece.setWorkGroupId (outputDetail.getWorkGroupId ());
        bpoBillPiece.setProcessId (outputDetail.getProcessId ());
        bpoBillPiece.setProductId (outputDetail.getProductId ());
        bpoBillPiece.setPersonId (employee.getPersonId ());
        bpoBillPiece.setBillingCycle ("2020-"+random (12)+random (30));
        bpoBillPiece.setSupplierInfoId (output.getSupplierId ());
        bpoBillPiece.setProjectId (output.getProjectId ());
        bpoBillPiece.setFeeRuleType (2);
        bpoBillPiece.setPrice (employee.getPrice ());
        BigDecimal price = new BigDecimal (employee.getPrice ().toString ());
        bpoBillPiece.setIncome (employee.getPrice ().add (price));
        BpoProductPrice byProductId = productPriceMapper.selectByProductId (outputDetail.getProductId ());
        bpoBillPiece.setUpperLimit (byProductId.getUpperLimit ());
        bpoBillPiece.setLowerLimit (byProductId.getLowerLimit ());
        bpoBillPiece.setPricingIntervalId (byProductId.getId ());
        bpoBillPiece.setRealIncome (employee.getPrice ().add (price));
        bpoBillPieceMapper.insertSelective (bpoBillPiece);
    }

    private Integer random(Integer num){
        return (int)(Math.random ()*(num+1));
    }

    @Override
    public List<BpoOutputDetailEmployeeShow> getEmployeeDetailList(Map<String, Object> params) {
        List<BpoOutputDetailEmployeeShow> list = outputDetailEmployeeMapper.getEmployeeDetailList(params);
        return list;
    }

    private int addOutput(BpoOutputParam outputParam) {
        BpoOutput output = new BpoOutput();
        output.setTransferStationId(outputParam.getTransferStationId());
        output.setProjectId(outputParam.getProjectId());
        output.setType(outputParam.getType());
        if (outputParam.getType().equals(DictDataConstants.OUTPUT_SUPPLIER)) {
            output.setSupplierId(outputParam.getSupplierId());
        }
        output.setPeriodStartTime(outputParam.getPeriodStartTime());
        output.setPeriodEndTime(outputParam.getPeriodEndTime());
        outputMapper.insertSelective(output);
        return output.getId();
    }

    private void addOutputDetail(BpoOutputParam outputParam,Integer outputId) {
        for (ProjectConfigListShow configShow : outputParam.getConfigListShows()) {
            BpoOutputDetail outputDetail = new BpoOutputDetail();
            outputDetail.setOutputId(outputId);
            outputDetail.setWorkModelId(configShow.getWorkModelId());
            outputDetail.setWorkGroupId(configShow.getWorkGroupId());
            outputDetail.setProcessId(configShow.getProcessId());
            outputDetail.setProductId(configShow.getProductId());
            outputDetail.setProductPriceId(configShow.getProductPriceId());
            outputDetail.setOutput(configShow.getOutput());
            outputDetailMapper.insertSelective(outputDetail);
        }

    }
}
