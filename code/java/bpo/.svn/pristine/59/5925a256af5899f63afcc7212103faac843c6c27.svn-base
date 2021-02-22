package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.bpoPerson.BpoPersonCheckParam;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonCheckShow;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonDetailShow;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BpoPersonCheckService {
    List<BpoPersonCheckShow> findAll(BpoPersonCheckParam checkParam);

    Result personCheckDetail(Integer personId);

    Result checkPerson(BpoPersonDetailShow personDetailShow);

    Result uploadContractFiles(MultipartFile[] files);

    Result uploadPersonFiles(MultipartFile[] files);

    Result deletePersonFile(String url);

    Result deleteContractFile(String url);

    Result synClientNo();

    Result findAllNation();

    Result findAllStation();

    Result findCurrentUserProject();
}
