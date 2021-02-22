package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoProject;
import com.rz.iot.bpo.model.show.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BpoProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoProject record);

    int insertSelective(BpoProject record);

    BpoProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoProject record);

    int updateByPrimaryKey(BpoProject record);

    BpoProjectDetailShow findProjectDetail(int id);

    ProjectConfigShow getProjectConfig(int id);

    List<ProjectConfigListShow> getProjectConfigList(int id);

    ProjectComputationalFeeRuleShow getComputationalFeeRuleByNumber(int id);

    ProjectComputationalFeeRuleShow getComputationalFeeRuleByTime(int id);

    List<BpoProjectEvaluationShow> findPartyBProjectEvaluation(Integer partyBId);

    List<Integer> selectContractIdsByProjectIds(List<Integer> projectIds);

    List<BpoProjectDetailListShow> findProjectByOrgId(@Param("orgId") Integer orgId,@Param("transferStationId")Integer transferStationId);

    List<BpoProjectSimpleList> getAllByLoginUserId(@Param("userId")Integer userId);

    List<BpoProjectListShow> getList(@Param("param")BpoProjectListShow param,@Param("userId")Integer userId);

    // 根据场地信息获取项目信息
    List<Map<String,Object>> findProjectByStationId(@Param("userId") Integer userId, @Param("stationId") Integer stationId);
}