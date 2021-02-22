package com.rz.iot.bpo.mina.service;

import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.show.MinaProjectDetailAllShow;
import com.rz.iot.bpo.mina.model.show.MinaProjectDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaProjectListShow;
import com.rz.iot.bpo.model.show.BpoProjectDetailAllShow;
import com.rz.iot.bpo.model.show.BpoProjectDetailShow;
import com.rz.iot.bpo.model.show.BpoProjectListShow;
import com.rz.iot.bpo.model.show.BpoSupplierSimpleList;

public interface MinaProjectService {

    Result getList(MinaProjectListShow param, Page<MinaProjectListShow> p);

    Result<MinaProjectDetailShow> detail(int id);

    Result<MinaProjectDetailAllShow> detailAll(int id);

}
