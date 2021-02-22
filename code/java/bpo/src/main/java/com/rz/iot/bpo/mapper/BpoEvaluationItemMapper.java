package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoEvaluationItem;
import com.rz.iot.bpo.model.show.BpoEvaluationShow;
import org.apache.ibatis.annotations.Param;

public interface BpoEvaluationItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoEvaluationItem record);

    int insertSelective(BpoEvaluationItem record);

    BpoEvaluationItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoEvaluationItem record);

    int updateByPrimaryKey(BpoEvaluationItem record);

    BpoEvaluationShow findEvaluationShowByProjectId(int projectId);

    BpoEvaluationShow findEvaluationShowBySupplierInfoId(@Param("supplierInfoId")Integer supplierInfoId);

}