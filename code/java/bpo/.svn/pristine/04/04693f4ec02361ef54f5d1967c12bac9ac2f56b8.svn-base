package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysAreaRel;
import com.rz.iot.bpo.model.SysProvince;
import com.rz.iot.bpo.service.ProvinceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 :
 * 作者 : xuxiake
 * 创建时间 : 2020/6/19 9:56
 * <p>
 * 修改原因 :
 * 修改人 : xuxiake
 * 修改时间 ：2020/6/19 9:56
 */
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Resource
    private ProvinceService provinceService;

    /**
     * 获取所有省
     * @return
     */
    @RequestMapping("/getAllProvince")
    public Result<List<SysProvince>> getAllProvince() {
        return provinceService.getAllProvince();
    }

    /**
     * 获取市
     * @param code
     * @return
     */
    @RequestMapping("/getCity")
    public Result<List<SysProvince>> getCity(String code) {
        return provinceService.getCity(code);
    }

    /**
     * 获取区
     * @param code
     * @return
     */
    @RequestMapping("/getDistrict")
    public Result<List<SysProvince>> getDistrict(String code) {
        return provinceService.getDistrict(code);
    }

    /**
     * 获取关联关系所有省
     */
    @RequestMapping("/getRelProvinces")
    public Result<SysAreaRel> getRelProvinces(){
        return provinceService.getRelProvinces();
    }

    /**
     * 通过省code获取市信息
     * @param code 省code
     * @return
     */
    @RequestMapping("/getRelCityByPCode")
    public Result getRelCityByPCode(String code){
        return provinceService.getRelCityByPCode(code);
    }

    /**
     * 通过市code获取区信息
     */
    @RequestMapping("/getRelDisByCCode")
    public Result getRelDisByCCode(String code){
        return provinceService.getRelDisByCCode(code);
    }

    /**
     * 通过areaId获取省市区信息
     */
    @RequestMapping("/getProCityDis")
    public Result getProCityDisByAreaId(Integer areaId){
        return provinceService.getProCityDisByAreaId(areaId);
    }
}
