package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoBillPiece;

public interface BpoBillPieceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoBillPiece record);

    int insertSelective(BpoBillPiece record);

    BpoBillPiece selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoBillPiece record);

    int updateByPrimaryKey(BpoBillPiece record);
}