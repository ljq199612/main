package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.BpoCustomerInfos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoCustomerInfosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoCustomerInfos record);

    int insertSelective(BpoCustomerInfos record);

    BpoCustomerInfos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoCustomerInfos record);

    int updateByPrimaryKey(BpoCustomerInfos record);

    //模糊查询根据用户id查询所有部门下的客户
    List<BpoCustomerInfos> CustomerByCondition(BaseEntity entity,BpoCustomerInfos infos);

    //客户信息表id和项目id查询单条数据
    BpoCustomerInfos findByIdAndProjectID(BpoCustomerInfos infos);

    //判断是否可被删除
    List<BpoCustomerInfos> findIsCanBeDelete(Integer id);

    List<BpoCustomerInfos> findAllByLoginUserId(@Param("param")BaseEntity param);

    BpoCustomerInfos selectByOrgId(Integer orgId);

    // 根据组织id查询客户类型
    String findTypeByCompanyId(Integer companyId);

    // 组织修改,根据组织id修改客户类型
    void updateByCompanyId(BpoCustomerInfos bpoCustomerInfos);
}