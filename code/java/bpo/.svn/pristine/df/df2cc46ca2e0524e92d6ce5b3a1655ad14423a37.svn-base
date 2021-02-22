package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoProduct;
import com.rz.iot.bpo.model.show.BpoProjectDetailListShow;
import org.apache.ibatis.annotations.Param;

public interface BpoProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoProduct record);

    int insertSelective(BpoProduct record);

    BpoProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoProduct record);

    int updateByPrimaryKey(BpoProduct record);

    //假删除
    int delPorjectById(Integer integer);


    BpoProduct findByNameAndProcessId(@Param("productName") String productName,@Param("processId") Integer processId);
}