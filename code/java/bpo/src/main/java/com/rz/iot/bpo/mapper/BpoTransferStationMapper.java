package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.BpoTransferStation;
import com.rz.iot.bpo.model.param.BpoTransferStationParam;
import com.rz.iot.bpo.model.show.BpoTSFDetail;
import com.rz.iot.bpo.model.show.BpoTransferStationShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoTransferStationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoTransferStation record);

    int insertSelective(BpoTransferStation record);

    BpoTransferStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoTransferStation record);

    int updateByPrimaryKey(BpoTransferStation record);

    List<BpoTransferStationShow> findAll(BpoTransferStationParam bpoTransferStationParam);

    BpoTransferStation isExistCode(BpoTransferStation bpoTransferStation);

    BpoTSFDetail detail(Integer id);

    List<BpoTransferStation> findAllByCityId(@Param("sysAreaId") Integer sysAreaId);

    List<BpoTransferStation> findAllTrans();

    //通过登录用户获取当前部门及一下 所有中转场信息
    List<BpoTransferStation> findAllByLoginUserId(@Param("param") BaseEntity param);

    // 新增部门与场地之间的关联关系
    void insertDeptStaRel(@Param("stationId") Integer id,@Param("deptId") Integer deptId);

    void updateStatus(@Param("id") Integer id,@Param("status") Byte status);
}