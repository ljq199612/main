package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoBillReconciliationTimeDetailed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoBillReconciliationTimeDetailedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoBillReconciliationTimeDetailed record);

    int insertSelective(BpoBillReconciliationTimeDetailed record);

    BpoBillReconciliationTimeDetailed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoBillReconciliationTimeDetailed record);

    int updateByPrimaryKey(BpoBillReconciliationTimeDetailed record);

    List<BpoBillReconciliationTimeDetailed> selectByPersonIdAndTime(Integer id, String date);

    int insertForeach(List<BpoBillReconciliationTimeDetailed> list);

    /**
     * 批量添加
     * @param detaileds 数据
     * @return
     */
 
    int insertSelectiveAll(@Param ("detaileds") List<BpoBillReconciliationTimeDetailed> detaileds);

    List<BpoBillReconciliationTimeDetailed> selectByParam(Integer companyId,Integer projectId,String billCycle);
}