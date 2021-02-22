package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.BpoBillTime;
import com.rz.iot.bpo.model.param.BillSelectPersonParam;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonDayTotal;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonDayDetail;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonMonthShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillTimeShow;
import com.rz.iot.bpo.model.show.BpoBillPayablePersonShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoBillTimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoBillTime record);

    int insertSelective(BpoBillTime record);

    BpoBillTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoBillTime record);

    int updateByPrimaryKey(BpoBillTime record);

    int getPeopleCount(@Param("date") String date, @Param("projectId") Integer projectId, @Param("personCompanyId") Integer personCompanyId);

    //根据供应商id和时间，获取此供应商下的员工账单记录
    List<BpoBillTimeShow> selectByIdAndTime(Integer companyId,Integer projectId,String time);

    List<BpoBillPayablePersonShow> findAllBillPerson(BillSelectPersonParam select);

    List<BpoBillPersonMonthShow> findAllBillMonth(BillSelectPersonParam select);

    
    List<BpoBillPersonDayDetail>  billPersonDayDetail(@Param("personId")Integer id,@Param("projectId") Integer projectId, @Param("date")String date);

    
    BpoBillPersonDayTotal getBillPersonTotal(@Param("personId") Integer personId,@Param("projectId") Integer projectId, @Param("date")String date);

    List<BpoBillPersonDayDetail> findBillTimeByTimeAndId(Integer personId, Integer projectId ,String date);

    //根据供应商id和时间，获取此供应商下的员工账单详细记录
    List<BpoBillTime> findAllByCompanyAndTime(Integer companyId,Integer projectId,String time);
    List<BpoBillTime> findAllByBillCycle(BaseEntity entity,String billCycle);
}