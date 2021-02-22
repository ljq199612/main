package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoContractInfo;
import com.rz.iot.bpo.model.param.BpoContractInfoParam;
import com.rz.iot.bpo.model.param.ContractInfoParam;
import com.rz.iot.bpo.model.show.BpoContractDetailShow;
import com.rz.iot.bpo.model.show.BpoContractQueryShow;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ContractInfoService {

    List<BpoContractQueryShow> findAll(ContractInfoParam contractInfoParam);

    //构建分页查询参数
    void buildQueryParams(ContractInfoParam contractInfoParam);

    Result insert(BpoContractInfoParam bpoContractInfoParam);

    BpoContractInfo selectByContractId(Integer id);

    BpoContractDetailShow selectDetailById(Integer id);

    void update(BpoContractDetailShow contractDetailShow);

    void delete(Integer id);

    void deletePaymentInfo(Integer id);

    List<Map<String,Object>> getBySupplierOrgId(Integer supplierOrgId);

    Result upload(MultipartFile file);

    void download(String path, String contractName, HttpServletRequest request, HttpServletResponse response) throws IOException;

    Result<BpoContractInfo> getbySupplierInfoId(Integer supplierInfoId);
}
