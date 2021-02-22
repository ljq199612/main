package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.SysRoleMapper;
import com.rz.iot.bpo.model.SysRole;
import com.rz.iot.bpo.model.param.SysRoleParam;
import com.rz.iot.bpo.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 系统角色业务
 * 作者 : Rycony
 * 创建时间 : 2020/6/21 14:25
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询所有角色
     * @param sysRole 查询参数
     * @return
     */
    @Override
    public List<SysRole> findAll(SysRole sysRole) {
        List<SysRole> list = sysRoleMapper.findAll(sysRole);
        return list;
    }


    /**
     * 新增角色
     * @param sysRole 角色参数
     * @return
     */
    @Override
    public Result insert(SysRole sysRole) {
        if(sysRole == null || sysRole.getRoleKey() == null)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        if(isExistCode(sysRole))
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        sysRoleMapper.insertSelective(sysRole);
        return Result.success(null,"新增系统角色成功");
    }

    /**
     * 更新系统角色
     * @param sysRoleParam 角色参数
     * @return
     */
    @Override
    public Result update(SysRoleParam sysRoleParam) {
        SysRole sysRole = new SysRole();
        sysRole.setStatus(sysRoleParam.getStatus());
        sysRole.setId(sysRoleParam.getId());
        sysRole.setRoleKey(sysRoleParam.getRoleKey());
        sysRole.setRoleName(sysRoleParam.getRoleName());
        sysRole.setRemark(sysRoleParam.getRemark());

        if(sysRole.getId() == null || sysRole.getRoleKey() == null)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        if(isExistCode(sysRole))
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return Result.success(null,"更新系统角色成功");
    }

    /**
     * 删除系统角色
     * @param sysRole 删除系统角色
     * @return
     */
    @Override
    public Result delete(SysRole sysRole) {
        if(sysRole == null)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);

        sysRole.setStatus(DictDataConstants.DELETE_STATUS);
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return Result.success(null,"删除系统角色成功");
    }

    /**
     * 查询所有角色-部分也
     * @return
     */
    @Override
    public Result findAllNoPage() {
        List<SysRole> list = sysRoleMapper.findAllNoPage();
        return Result.success(list);
    }

    /**
     * 角色详情
     * @param sysRole
     * @return
     */
    @Override
    public Result detail(SysRole sysRole) {
        if(sysRole == null && sysRole.getId() == null)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        SysRole detailSysRole = sysRoleMapper.detail(sysRole.getId());
        return Result.success(detailSysRole);
    }

    /**
     * 判断编码是否存在
     * @param sysRole 编码
     * @return
     */
    public boolean isExistCode(SysRole sysRole){
        SysRole findSysRole = sysRoleMapper.isExistCode(sysRole);
        boolean flag = false;
        if(findSysRole != null){
            flag = true;
        }
        return flag;
    }
}
