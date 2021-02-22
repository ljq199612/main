package com.rz.iot.bpo.mina.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.MinaPerson;
import com.rz.iot.bpo.mina.model.param.MinaLoginParm;
import com.rz.iot.bpo.mina.model.show.PersonCenterShow;
import com.rz.iot.bpo.mina.model.show.WorkExperienceShow;

import java.util.List;

/**
 * @program: bpo-preview
 * @description: 个人中心
 * @author: YangShun
 * @create: 2020-07-22 17:49
 **/
public interface MinaPersonCenterService {
    Result<PersonCenterShow> getLinkMode();

    Result<List<WorkExperienceShow>> getWorkExperience();

    Result changePassword(MinaLoginParm parm);

    Result forgetPassword(MinaLoginParm parm);

    Result login(String code,MinaLoginParm parm);

    Result signUp(MinaPerson person);

    Result getInfo();
}
