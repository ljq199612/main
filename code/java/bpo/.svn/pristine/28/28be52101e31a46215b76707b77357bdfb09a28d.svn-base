package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.mina.model.show.MinaSupplierDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaSupplierListShow;
import com.rz.iot.bpo.model.show.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MinaSupplierMapper {

    List<MinaSupplierListShow> list(@Param("info") MinaSupplierListShow info, @Param("param") BaseEntity param);

    List<BpoSupplierSimpleList> getSupplierListByPorjectId(@Param("projectId")Integer projectId);

    SupplierDetailShow detail(@Param("id") Integer id);

    BpoSupplierListRelatedShow findSupplierByProjectId(@Param("projectId") Integer projectId, @Param("orgId")Integer orgId);
}
