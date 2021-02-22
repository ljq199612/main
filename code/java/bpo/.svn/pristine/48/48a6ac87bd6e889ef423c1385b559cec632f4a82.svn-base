package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoPerson;
import com.rz.iot.bpo.model.param.SysNation;
import com.rz.iot.bpo.model.param.bpoPerson.BpoPersonParam;
import com.rz.iot.bpo.model.show.bpoPerson.BpoBasePerson;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonDetailShow;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonShow;

import java.util.List;

public interface BpoPersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoPerson record);

    int insertSelective(BpoPerson record);

    BpoPerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoPerson record);

    int updateByPrimaryKey(BpoPerson record);

    BpoPerson selectByIdCard(String idCard);

    List<BpoPersonShow> findAllPerson(BpoPersonParam personParam);

    BpoPersonDetailShow findBasePerson(Integer id);

    BpoPerson selectByUserId(Integer userId);


    void addPersonsBaseInfo(List<BpoBasePerson> importPerson);

    List<SysNation> getNations();
}