package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.BpoBillSupplierDay;
import com.rz.iot.bpo.model.param.bpoBill.BillSelectParam;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillDayShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoBillSupplierDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoBillSupplierDay record);

    int insertSelective(BpoBillSupplierDay record);

    BpoBillSupplierDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoBillSupplierDay record);

    int updateByPrimaryKey(BpoBillSupplierDay record);

    List<BpoBillDayShow> selectAllDayAccount(BaseEntity entity, BillSelectParam param);

    BpoBillDayShow selectDetailById(Integer id);

	BpoBillSupplierDay selectDayBill(@Param("projectId") Integer projectId, @Param("date") String date,@Param("payerId") Integer payerId,@Param("payeeId") Integer payeeId);

	int updateReconciliationStatus(Integer status,String billCycle);
}