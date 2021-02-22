package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysProvince;

import java.util.List;

/**
 * 描述 :
 * 作者 : xuxiake
 * 创建时间 : 2020/6/19 9:58
 * <p>
 * 修改原因 :
 * 修改人 : xuxiake
 * 修改时间 ：2020/6/19 9:58
 */
public interface ProvinceService {

    /**
     * 获取所有省
     * @return
     */
    Result<List<SysProvince>> getAllProvince();

    /**
     * 获取市
     * @param code
     * @return
     */
    Result<List<SysProvince>> getCity(String code);

    /**
     * 获取区
     * @param code
     * @return
     */
    Result<List<SysProvince>> getDistrict(String code);

    /**
     * 获取关联关系所有省
     */
    Result getRelProvinces();

    /**
     * 通过省code获取市信息
     * @return
     */
    Result getRelCityByPCode(String code);

    Result getRelDisByCCode(String cCode);

    Result getProCityDisByAreaId(Integer areaId);
}
