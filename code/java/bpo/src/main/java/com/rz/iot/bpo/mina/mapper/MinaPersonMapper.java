package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.mina.model.MinaPerson;
import com.rz.iot.bpo.mina.model.show.MinaPersonShow;

import java.util.List;

public interface MinaPersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MinaPerson record);

    int insertSelective(MinaPerson record);

    MinaPerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MinaPerson record);

    int updateByPrimaryKey(MinaPerson record);

    List<MinaPersonShow> findAll(BaseEntity entity, String select);

    MinaPersonShow getDetail(Integer id);

    MinaPersonShow getPerson(Integer id);

    MinaPersonShow getHead(Integer id);
}