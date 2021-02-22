package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoBillPersonMonth;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonReconciliationShow;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import com.rz.iot.bpo.model.param.BillSelectPersonParam;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonMonthShow;
import java.util.List;

public interface BpoBillPersonMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoBillPersonMonth record);

    int insertSelective(BpoBillPersonMonth record);

    BpoBillPersonMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoBillPersonMonth record);

    int updateByPrimaryKey(BpoBillPersonMonth record);

    BpoBillPersonMonth selectMonthBill(@Param("projectId") Integer projectId, @Param("date") String date, @Param("personId") Integer personId);

    List<BpoBillPersonMonthShow> findAllBillMonth(BillSelectPersonParam select);

    BpoBillPersonMonthShow findMonthById(Integer monthId);

    BpoBillPersonReconciliationShow getMonthById(Integer monthId);

}