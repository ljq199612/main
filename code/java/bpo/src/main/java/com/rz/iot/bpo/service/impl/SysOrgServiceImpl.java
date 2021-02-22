package com.rz.iot.bpo.service.impl;

import com.github.pagehelper.PageHelper;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.SysOrgMapper;
import com.rz.iot.bpo.model.SysOrg;
import com.rz.iot.bpo.service.SysOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 系统组织类型业务实现
 * 作者 : Rycony
 * 创建时间 : 2020/6/19 18:35
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service
public class SysOrgServiceImpl implements SysOrgService {
    @Resource
    private SysOrgMapper sysOrgMapper;

    @Override
    public Result findAllType() {
        List<SysOrg> list = sysOrgMapper.findAllType();

        /**
         * 按需求:
         *  1.非超级管理员只能获取类型为客户和供应商
         */
        // 获取当前用户
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return Result.success(list);
    }
}
