package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.mapper.BpoPersonContractInfoMapper;
import com.rz.iot.bpo.model.BpoPersonContractInfo;
import com.rz.iot.bpo.model.show.BpoPersonContractInfoShow;
import com.rz.iot.bpo.service.BpoPersonContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BpoPersonContractServiceImpl implements BpoPersonContractService {

    @Resource
    private BpoPersonContractInfoMapper personContractInfoMapper;


    @Override
    public void insert(BpoPersonContractInfo personContractInfo) {
        personContractInfoMapper.insert(personContractInfo);
    }

    @Override
    public BpoPersonContractInfoShow getDetail(Integer jiaId, Integer yiId) {
        return personContractInfoMapper.selectDetail(jiaId,yiId);
    }

    @Override
    public void update(BpoPersonContractInfo personContractInfo) {
        personContractInfoMapper.updateByPrimaryKey(personContractInfo);
    }
}
