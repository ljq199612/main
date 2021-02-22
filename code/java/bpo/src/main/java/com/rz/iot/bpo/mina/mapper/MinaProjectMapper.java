package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.mina.model.show.MinaProjectDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaProjectListShow;
import com.rz.iot.bpo.model.show.BpoProjectDetailShow;
import com.rz.iot.bpo.model.show.BpoProjectListShow;

import com.rz.iot.bpo.model.show.ProjectConfigShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MinaProjectMapper {

    List<MinaProjectListShow> getList(@Param("param")MinaProjectListShow param, @Param("userId")Integer userId);

    MinaProjectDetailShow findProjectDetail(int id);

    ProjectConfigShow getProjectConfig(int id);
}
