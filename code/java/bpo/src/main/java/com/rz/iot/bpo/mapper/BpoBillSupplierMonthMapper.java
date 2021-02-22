package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.BpoBillSupplierMonth;
import com.rz.iot.bpo.model.param.bpoBill.BillSelectParam;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillMonthShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillReconciliationShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillSupplierShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoBillSupplierMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoBillSupplierMonth record);

    int insertSelective(BpoBillSupplierMonth record);

    BpoBillSupplierMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoBillSupplierMonth record);

    int updateByPrimaryKey(BpoBillSupplierMonth record);

    List<BpoBillMonthShow> selectAllMonthAccount(BaseEntity entity, BillSelectParam param);

    BpoBillSupplierMonth selectMonthBill(@Param("projectId") Integer projectId, @Param("date") String date, @Param("payerId") Integer payerId, @Param("payeeId") Integer payeeId);

    //通过companyid获取供应商id
    BpoBillSupplierShow selectSupplierIdByCompanyId(Integer payeeId ,Integer projectId);

    BpoBillReconciliationShow selectReconciliationByKey(Integer monthId);

    int updateReconciliationStatus(Integer status,String billCycle);
}