package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.mina.model.param.MinaLoginParm;
import com.rz.iot.bpo.mina.model.show.PersonCenterShow;
import com.rz.iot.bpo.mina.model.show.WorkExperienceShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MinaPersonCenterMapper {

    PersonCenterShow getLinkMode(Integer userId);

    List<WorkExperienceShow> getWorkExperience(@Param ("userId") Integer userId);

    MinaLoginParm getUserById(Integer id);
}
