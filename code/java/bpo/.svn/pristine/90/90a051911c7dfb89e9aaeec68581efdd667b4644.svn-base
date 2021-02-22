package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoContractInfo;
import com.rz.iot.bpo.model.param.ContractInfoParam;
import com.rz.iot.bpo.model.show.BpoContractDetailShow;
import com.rz.iot.bpo.model.show.BpoContractQueryShow;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BpoContractInfoMapper {

    List<BpoContractQueryShow> findAll(ContractInfoParam contractInfoParam);

    int deleteByPrimaryKey(Integer id);

    int insert(BpoContractInfo record);

    int insertSelective(BpoContractInfo record);

    BpoContractInfo selectByPrimaryKey(Integer id);

    BpoContractInfo selectByContractCode(String contractCode);

    int updateByPrimaryKeySelective(BpoContractInfo record);

    int updateByPrimaryKey(BpoContractInfo record);

    List<BpoContractInfo> findContractInfoByPartyAIdAndPartyBId(@Param("partyAId") int partyAId, @Param("partyBId") Integer partyBId);

	BpoContractDetailShow selectDetailById(Integer id);

	List<Map<String,Object>> getBySupplierOrgId(@Param("supplierOrgId")Integer supplierOrgId, @Param("companyIds")Set<Integer> companyIds);

    BpoContractInfo getbySupplierInfoId(@Param("supplierInfoId")Integer supplierInfoId);
}