package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.show.personClass.BpoPersonArrangeParam;
import com.rz.iot.bpo.model.show.personClass.BpoPersonArrangeShow;

import java.util.List;

/**
 * 描述 : 人员安排业务
 * 作者 : Rycony
 * 创建时间 : 2020/7/29 11:45
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
public interface BpoClassesGenerateService {
    List<BpoPersonArrangeShow> findAll(BaseEntity baseEntity,Integer stationId,Integer projectId);

    // 查询所有场地信息
    Result findStations(BaseEntity baseEntity);

    // 根据场地id查询对应项目信息
    Result findProjects(Integer stationId);

    // 人员安排详情
    Result findArrangeDetail(Integer classId);

    // 排班完成
    Result finish(BpoPersonArrangeParam param);
}
