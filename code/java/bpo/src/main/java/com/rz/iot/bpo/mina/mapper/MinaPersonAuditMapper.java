package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.mina.model.param.MinaAuditParam;
import com.rz.iot.bpo.mina.model.show.MinaDeptShow;
import com.rz.iot.bpo.mina.model.show.MinaPersonShow;
import com.rz.iot.bpo.model.BpoProject;
import com.rz.iot.bpo.model.SysCompany;

import java.util.List;

/**
 * @program: bpo-preview
 * @description: 人员审核 小程序
 * @author: YangShun
 * @create: 2020-07-20 17:19
 **/
public interface MinaPersonAuditMapper {
    /**
     * 查询所有未审核的
     * @return
     */
    List<MinaPersonShow> findAll(BaseEntity entity, String select);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    MinaPersonShow getDetail(Integer id);

    /**
     * 获取用户所属部门
     * @param entity
     * @return
     */
    List<MinaDeptShow> getDept(BaseEntity entity);

    SysCompany getCompany(Integer deptId);

    List<BpoProject> getProject(BaseEntity entity);

    int updateParam(MinaAuditParam param);
}
