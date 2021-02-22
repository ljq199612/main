package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.SysDept;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> getAll();

    //超级管理员的查询
    List<SysDept> findAllbySuperAdmin(@Param("status") Integer status, @Param ("name") String name);

    //通过user_id查询对应的组织架构
    List<SysDept> findAll(BaseEntity entity,@Param ("status") Integer status,@Param ("name") String name);

    //插入客户-部门关联信息
    int insertCustomRelation(SysDept dept);

    //更新客户-部门关联信息
    int updateCustomRelation(SysDept dept);

    /**
     * 删除客户-部门关联信息
     * @param id 客户信息表中的id
     * @return
     */
 	//用用户信息表的id删除关联信息
    int updateByCustomId(Integer id);

    //查询当前用户可以操作的组织架构
    List<SysDept> findCanControl(BaseEntity entity);

    //查询当前组织下的供应商组织
    List<SysDept> findAllOther(BaseEntity entity, @Param ("status") Integer status, @Param ("name") String name);

    /**
     * 插入新数据后更新上级部门的descendants
     * @param id 插入数据的parentID
     * @param append 插入的字符串（,加id） 如",21"
     * @return
     */
    int updateParent(@Param ("id") Integer id, @Param ("append") String append);

    /**
     * 删除数据后更新上级部门的descendants
     * @param id 删除数据的parentID
     * @param name 插入的id字符串（,加id） 如",21"
     * @return
     */
    int updateAfterDelete(@Param ("id") Integer id ,@Param ("name") String name,@Param ("parentId")Integer parentId);

    //查询dept_id是否被关联
    List<Integer> selectIsOrNotConnect(Integer id);

    /**
     * 条件查询
     * @param dept
     * @return
     */
    List<SysDept> getCondition(SysDept dept);

    //根据dept_id查询所属上级组织信息
    List<SysDept> getByDeptId(Integer deptId);


    // 根据组织id查询顶级部门
    SysDept selectByCompanyId(Integer companyId);

    // 根据当前用户部门层级关系
    List<SysDeptShow> findAllSysDeptByUserId(Integer parentId);

    List<SysDept> findAllTopDept();

    List<List<SysDeptShow>> adminFindAllSysDept(@Param("list") List<SysDept> topDept);

    SysDeptShow upFindOrgByDeptId(@Param("deptId") Integer deptId,@Param("isTop")Integer isTop);

    SysDeptShow findUpDept(@Param("deptId") Integer deptId);

    int updateStatus(SysDept dept);


    void addDeptUserRel(@Param("userId") Integer userId,@Param("deptId") Integer deptId);

    void updateDeptUserRel(@Param("userId") Integer userId,@Param("deptId") Integer deptId);

    // 通过用户查询部门的详细信息
    SysDept findByUserId(Integer userId);
}