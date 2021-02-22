package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.mina.model.MinaAbnormal;
import com.rz.iot.bpo.mina.model.show.MinaAbnormalShow;
import com.rz.iot.bpo.model.BpoPerson;
import com.rz.iot.bpo.model.BpoProject;
import com.rz.iot.bpo.model.BpoTransferStation;
import com.rz.iot.bpo.model.SysCompany;

import java.math.BigDecimal;
import java.util.List;

public interface MinaAbnormalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MinaAbnormal record);

    int insertSelective(MinaAbnormal record);

    MinaAbnormal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MinaAbnormal record);

    int updateByPrimaryKey(MinaAbnormal record);

    List<MinaAbnormalShow> findAll(String select,BaseEntity entity);

    MinaAbnormalShow getDetail(Integer id);

    List<BpoProject> getProject(BaseEntity entity);

    List<BpoTransferStation> getTransfer(BaseEntity entity);

    List<SysCompany> getSupplier(BaseEntity entity);

    List<BpoPerson> getPerson(Integer projectId);

    BigDecimal getSupplierMoneyByCompanyId(Integer companyId);
}