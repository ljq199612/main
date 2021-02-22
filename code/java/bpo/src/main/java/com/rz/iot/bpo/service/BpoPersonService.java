package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.bpoPerson.BpoPersonParam;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonDetailShow;
import com.rz.iot.bpo.model.show.bpoPerson.BpoPersonShow;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BpoPersonService {
    Result findPersonCom();

    List<BpoPersonShow> findAllPerson(BpoPersonParam personParam);

    Result lookIdCard(Integer personId);

    Result detailPerson(Integer id);

    Result updatePerson(BpoPersonDetailShow bpoPersonDetailShow);

    Result downLoadContractFile(String url,String fileName,HttpServletResponse response);

    Result findSysDeptShow();

    Result addPerson(BpoPersonDetailShow bpoPersonDetailShow);

    Result currentUserComOrg();

    Result exportPerson(BpoPersonParam personParam,HttpServletResponse response);

    Result exportPersonModel(HttpServletResponse response);

    Result importPerson(MultipartFile files);
}
