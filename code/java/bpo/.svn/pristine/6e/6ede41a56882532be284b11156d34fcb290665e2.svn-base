package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.Constants;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.SysWorkModuleRelMapper;
import com.rz.iot.bpo.model.SysWorkModuleRel;
import com.rz.iot.bpo.model.param.SysProOrGrpParam;
import com.rz.iot.bpo.model.param.SysWorkModuleRelParam;
import com.rz.iot.bpo.service.SysWorkModuleRelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 工作模块/工序/工作小组业务实现
 * 作者 : Rycony
 * 创建时间 : 2020/6/17 21:04
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service
@Log4j2
public class SysWorkModuleRelServiceImpl implements SysWorkModuleRelService {
    @Resource
    private SysWorkModuleRelMapper sysWorkModuleRelMapper;

    /**
     * 查询所有工作模块/工序/工作小组
     * @return
     */
    @Override
    public List<SysWorkModuleRelParam> findAll() {
        List<SysWorkModuleRelParam> list = sysWorkModuleRelMapper.findAll();
        return list;
    }
    /**
     * 新增工作模块
     * todo 需要定义类型工作模块/工序/工作小组 1/2/3
     * @param sysWorkModuleRel 工作模块参数
     * @return
     */
    @Override
    public Result insertSysWorkModuleRel(SysWorkModuleRel sysWorkModuleRel) {
        if(isExistSameCode(sysWorkModuleRel.getCode())){
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        }
        sysWorkModuleRel.setType(Constants.SYS_WORK_MODULE_TYPE);
        sysWorkModuleRelMapper.insertSelective(sysWorkModuleRel);
        return Result.success(null,"新增成功");
    }

    /**
     * 更新工作模块
     * @param sysWorkModuleRel 工作模块参数
     * @return
     */
    @Override
    public Result updateSysWorkModuleRel(SysWorkModuleRel sysWorkModuleRel) {
        if(isExistSameCode(sysWorkModuleRel.getCode())){
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        }
        sysWorkModuleRelMapper.updateByPrimaryKeySelective(sysWorkModuleRel);
        return Result.success(null,"更新成功");
    }

    /**
     * 新增工序/工作小组
     * @param sysProOrGrpParam 工序/工作小组参数
     * @return
     */
    @Override
    public Result insertSysProOrGrp(SysProOrGrpParam sysProOrGrpParam) {
        if(isExsitCode(sysProOrGrpParam))
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        // 新增
        sysWorkModuleRelMapper.insertSysProOrGrp(sysProOrGrpParam);
        return Result.success(null,"新增成功");
    }

    /**
     * 更新工序/工作小组
     * @param sysProOrGrpParam 工序/工作小组参数
     * @return
     */
    @Override
    public Result updateProOrGrp(SysProOrGrpParam sysProOrGrpParam) {
        if(isExsitCode(sysProOrGrpParam))
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        for(SysWorkModuleRel workModuleRel : sysProOrGrpParam.getList()){
            sysWorkModuleRelMapper.updateByPrimaryKeySelective(workModuleRel);
        }
        return Result.success(null,"更新成功");
    }

    /**
     * 判断
     * @param sysProOrGrpParam
     * @return
     */
    public boolean isExsitCode(SysProOrGrpParam sysProOrGrpParam) {
        boolean flag = false;
        List<SysWorkModuleRel> list = sysWorkModuleRelMapper.isExsitCode(sysProOrGrpParam);
        if(list != null && list.size() > 0){
            flag = true;
        }
        return flag;
    }

    /**
     * 工作模块是否存在相同的编码
     * @return
     */
    public boolean isExistSameCode(String code){
        SysWorkModuleRel sysWorkModuleRel = sysWorkModuleRelMapper.isExistSameCode(code);
        boolean flag = false;
        if(sysWorkModuleRel != null){
            flag = true;
        }
        return flag;
    }

    /**
     * 删除工序/工作小组
     * @param sysWorkModuleRel 工序/工作小组参数
     * @return
     */
    @Override
    public Result deleteProOrGrp(SysWorkModuleRel sysWorkModuleRel) {
        if(sysWorkModuleRel == null){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }
        sysWorkModuleRel.setStatus((byte)DictDataConstants.DELETE_STATUS);
        sysWorkModuleRelMapper.updateByPrimaryKeySelective(sysWorkModuleRel);
        return Result.success(null,"删除成功");
    }

}
