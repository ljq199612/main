package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoSupplier;
import org.apache.ibatis.annotations.Param;

public interface BpoSupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoSupplier record);

    int insertSelective(BpoSupplier record);

    BpoSupplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoSupplier record);

    int updateStatusByCompanyId(BpoSupplier record);

    int updateSupplierStatusByCompanyId(@Param("companyId")Integer companyId,@Param("supplierStatus")Integer supplierStatus);

    int updateByPrimaryKey(BpoSupplier record);
}