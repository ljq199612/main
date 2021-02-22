package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.SysAreaRelMapper;
import com.rz.iot.bpo.mapper.SysProvinceMapper;
import com.rz.iot.bpo.model.SysAreaRel;
import com.rz.iot.bpo.model.SysProvince;
import com.rz.iot.bpo.model.show.ShowArea;
import com.rz.iot.bpo.service.ProvinceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 :
 * 作者 : xuxiake
 * 创建时间 : 2020/6/19 9:59
 * <p>
 * 修改原因 :
 * 修改人 : xuxiake
 * 修改时间 ：2020/6/19 9:59
 */
@Service("provinceService")
@SuppressWarnings("unchecked")
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private SysProvinceMapper sysProvinceMapper;
    @Resource
    private SysAreaRelMapper sysAreaRelMapper;

    /**
     * 获取所有省
     * @return
     */
    @Cacheable(cacheNames = "province")
    @Override
    public Result<List<SysProvince>> getAllProvince() {

        List<SysProvince> allProvince = sysProvinceMapper.getAllProvince();
        return Result.success(allProvince);
    }

    /**
     * 获取市
     * @param code
     * @return
     */
    @Cacheable(cacheNames = "city")
    @Override
    public Result<List<SysProvince>> getCity(String code) {

        if (StringUtils.isAnyEmpty(code)) {
            return Result.requestParamError();
        }
        return Result.success(sysProvinceMapper.getCity(code));
    }

    /**
     * 获取区
     * @param code
     * @return
     */
    @Cacheable(cacheNames = "district")
    @Override
    public Result<List<SysProvince>> getDistrict(String code) {
        if (StringUtils.isAnyEmpty(code)) {
            return Result.requestParamError();
        }
        return Result.success(sysProvinceMapper.getDistrict(code));
    }

    /**
     * 获取关联关系所有省
     * @return
     */
    @Override
    public Result getRelProvinces() {
        List<SysAreaRel> list = sysAreaRelMapper.getAllSysAreaRelP();
        List<ShowArea> showAreas = new ArrayList();
        for(SysAreaRel sysAreaRel : list){
            ShowArea showArea = new ShowArea();
            showArea.setCode(sysAreaRel.getProvinceId());
            showArea.setName(sysAreaRel.getProvinceName());
            showAreas.add(showArea);
        }
        return Result.success(showAreas);
    }

    /**
     * 获取省code获取市信息
     * @param code 省code
     * @return
     */
    @Override
    public Result getRelCityByPCode(String code) {
        List<SysAreaRel> list = sysAreaRelMapper.getSysAreaRelC(code);
        List<ShowArea> showAreas = new ArrayList();
        for(SysAreaRel sysAreaRel : list){
            ShowArea showArea = new ShowArea();
            showArea.setCode(sysAreaRel.getCityId());
            showArea.setName(sysAreaRel.getCityName());
            showAreas.add(showArea);
        }
        return Result.success(showAreas);
    }

    /**
     * 通过市code获取区信息
     * @param cCode 市code
     * @return
     */
    @Override
    public Result getRelDisByCCode(String cCode) {
        List<SysAreaRel> list = sysAreaRelMapper.getSysAreaRelD(cCode);
        List<ShowArea> showAreas = new ArrayList();
        for(SysAreaRel sysAreaRel : list){
            ShowArea showArea = new ShowArea();
            showArea.setCode(sysAreaRel.getDistrictId());
            showArea.setName(sysAreaRel.getDistrictName());
            showAreas.add(showArea);
        }
        return Result.success(showAreas);
    }

    @Override
    public Result getProCityDisByAreaId(Integer areaId) {
        if(areaId == null || areaId == 0)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        SysAreaRel sysAreaRel = sysAreaRelMapper.selectByPrimaryKey(areaId);

        return Result.success(sysAreaRel);
    }
}
