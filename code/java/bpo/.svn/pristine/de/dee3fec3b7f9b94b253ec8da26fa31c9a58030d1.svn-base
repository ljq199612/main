package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysCompany;
import com.rz.iot.bpo.model.param.SysCompanyParam;
import com.rz.iot.bpo.model.show.SysCompanyShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysCompany record);

    int insertSelective(SysCompany record);

    SysCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCompany record);

    int updateByPrimaryKey(SysCompany record);

    List<SysCompanyShow> findAll(SysCompanyShow sysCompanyShow);

    SysCompany isExistCode(SysCompanyParam param);

    List<SysCompany> findAllComByKey(Integer orgType);

    List<SysCompany> findCompanyByOrgType(@Param("orgType") Integer orgType,@Param("orgTypeString") String orgTypeString);

    SysCompany findCompanyByUserId(Integer userId);

    List<Integer> selectChildIds(Integer id);
    // 查询所有人员公司集合
    List<Map<String,String>> findPersonCom();

    List<SysCompany> findAllByName(String name);

    List<SysCompany> findAlls();

    //通过detail_id查公司id
    SysCompany findByDetailId(Integer id);

    //根据detailid删除公司
    int deletebyDetailId(Integer id);

    // 查询公司详情
    SysCompanyParam detail(Integer companyId);

    Map<String,Object> selectInfoById(Integer id);

    Map<String,Object> selectInfoByDetailId(Integer id);

    void updateStatus(@Param("id") Integer id,@Param("status") Integer status);

    List<Map<String,Object>> selectByProject(@Param("projectId") Integer projectId);
}