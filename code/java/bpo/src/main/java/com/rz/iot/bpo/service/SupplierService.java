package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.BpoFeeRuleParam;
import com.rz.iot.bpo.model.param.SupplierAddParam;
import com.rz.iot.bpo.model.show.*;

import java.util.List;
import java.util.Map;

/**
 * 描述 : 供应商管理Service
 * 作者 : qin xian
 * 创建时间 : 2020/6/18 0018 10:12
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
public interface SupplierService {

    Result list(BaseEntity param,SupplierListShow info,Page<SupplierListShow> page);

    Result<SupplierDetailShow> detail(Integer id);

    Result add(SupplierAddParam info);

    Result update(SupplierAddParam info);

    Result delete(Integer companyId);

    Result updateSupplierStatus(Integer companyId,Integer supplierStatus);

    Result ruleList(BpoSupplierRuleListShow info);

    Result ruleDetail(BpoSupplierRuleDetailShow info);

    Result ruleDetailTime(BpoSupplierRuleDetailShow info);

    Result getInfoForAddFeeRule(Integer id);

    Result addFeeRule(List<BpoFeeRuleParam> list);

    Result deleteProductPrice(int[] id);

    Result findAllInDepByLoginUserId(BaseEntity param);

    Result findAllByLoginUserId(BaseEntity param);

    Result getSupplierListByPorjectId(Integer projectId);

    List<BpoProjectSupplierListShow> selectByProjectId(Integer projectId);

    BpoProjectSupplierListShow selectByPersonId(Integer personId);
}