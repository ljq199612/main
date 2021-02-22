package com.rz.iot.bpo.service.impl;

import com.github.pagehelper.PageHelper;
import com.rz.iot.bpo.common.constant.Constants;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.BpoPersonMapper;
import com.rz.iot.bpo.mapper.BpoTransferStationMapper;
import com.rz.iot.bpo.mapper.SysAreaRelMapper;
import com.rz.iot.bpo.mapper.SysDeptMapper;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.BpoTransferStationParam;
import com.rz.iot.bpo.model.show.BpoTSFDetail;
import com.rz.iot.bpo.model.show.BpoTransferStationShow;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import com.rz.iot.bpo.service.BpoTransferStationService;
import com.rz.iot.bpo.service.SysDictDataService;
import org.apache.commons.collections4.EnumerationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : 中转场业务实现
 * 作者 : Rycony
 * 创建时间 : 2020/6/17 14:32
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service("BpoTransferStationService")
public class BpoTransferStationServiceImpl implements BpoTransferStationService {
    @Resource
    private BpoTransferStationMapper bpoTransferStationMapper;
    @Resource
    private SysAreaRelMapper sysAreaRelMapper;
    @Resource
    private SysDictDataService sysDictDataService;
    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public Result insert(BpoTransferStationParam bpoTransferStationParam) {
        if(bpoTransferStationParam == null){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }
        BpoTransferStation bpoTransferStation = new BpoTransferStation();
        bpoTransferStation.setId(bpoTransferStationParam.getId());
        bpoTransferStation.setCode(bpoTransferStationParam.getTransferCode());

        if(isExistCode(bpoTransferStation))
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        BpoTransferStation station = isExsitAreaRel(bpoTransferStationParam);
        // 新增场地信息
        bpoTransferStationMapper.insertSelective(station);

        // 新增场地与部门之间的关联关系
        Integer deptId = bpoTransferStationParam.getDeptId();
        if(deptId != null && deptId != 0)
            bpoTransferStationMapper.insertDeptStaRel(station.getId(),deptId);

        return Result.success(null,"新增成功");
    }

    @Override
    public Result update(BpoTransferStationParam bpoTransferStationParam) {
        if(bpoTransferStationParam.getId() == null || bpoTransferStationParam.getTransferCode() == null){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }
        BpoTransferStation station = new BpoTransferStation();
        station.setId(bpoTransferStationParam.getId());
        station.setCode(bpoTransferStationParam.getTransferCode());

        if(isExistCode(station)){
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        }
        BpoTransferStation bpoTransferStation = isExsitAreaRel(bpoTransferStationParam);
        bpoTransferStationMapper.updateByPrimaryKeySelective(bpoTransferStation);
        return Result.success(null,"更新成功");
    }

    /**
     * 获取场地类型
     * @return
     */
    @Override
    public Result findStationType() {
        List<SysDictData> list = sysDictDataService.findAllByType(Constants.SYS_STATION_DICT);
        return Result.success(list);
    }

    /**
     * 查询所有分页查询
     * @param bpoTransferStationParam
     * @return
     */
    @Override
    @DataScope(deptSubRelAlias = "sub_rel")
    public List<BpoTransferStationShow> findAll(BpoTransferStationParam bpoTransferStationParam) {

        // 获取省市区关联id
        if(bpoTransferStationParam != null && bpoTransferStationParam.getProvinceId() != null && bpoTransferStationParam.getCityId() != null && bpoTransferStationParam.getDistrictId() != null){
            Integer areaRelId = isExisiAreaId(bpoTransferStationParam);
            bpoTransferStationParam.setAreaId(areaRelId);
        }
        // todo 后期需要从redis获取该数据
        bpoTransferStationParam.setDictType("sys_station");
        List<BpoTransferStationShow> list = bpoTransferStationMapper.findAll(bpoTransferStationParam);

        return list;
    }

    /**
     * 删除场地信息
     * @param bpoTransferStation 场地参数
     * @return
     */
    @Override
    public Result delete(BpoTransferStation bpoTransferStation) {
        if(bpoTransferStation == null && bpoTransferStation.getId() == null){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }
        bpoTransferStation.setStatus((byte)DictDataConstants.DELETE_STATUS);
        bpoTransferStationMapper.updateByPrimaryKeySelective(bpoTransferStation);
        return Result.success(null,"删除成功");
    }

    /**
     * 查询详情
     * @param bpoTransferStation 查询参数
     * @return
     */
    @Override
    public Result findDetail(BpoTransferStation bpoTransferStation) {
        if(bpoTransferStation == null && bpoTransferStation.getId() == null)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        BpoTSFDetail bpoTSFDetail = bpoTransferStationMapper.detail(bpoTransferStation.getId());
        return Result.success(bpoTSFDetail);
    }

    /**
     * 查询所有的中转场信息
     * @return
     */
    @Override
    public Result<List<BpoTransferStation>> findAll() {
        List<BpoTransferStation> all = bpoTransferStationMapper.findAllTrans ();
        Result<List<BpoTransferStation>> result=new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (all);
        return result;
    }

    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD)
    @Override
    public Result<List<BpoTransferStation>> findAllByLoginUserId(BaseEntity param) {
        List<BpoTransferStation> allTransByUserId = bpoTransferStationMapper.findAllByLoginUserId (param);
        Result<List<BpoTransferStation>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allTransByUserId);
        return result;
    }

    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT)
    @Override
    public Result<List<BpoTransferStation>> findAllInDepByLoginUserId(BaseEntity param) {
        List<BpoTransferStation> allTransByUserId = bpoTransferStationMapper.findAllByLoginUserId (param);
        Result<List<BpoTransferStation>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allTransByUserId);
        return result;
    }

    /***
     * 更改场地状态
     * @param param
     * @return
     */
    @Override
    public Result updateStatus(BpoTransferStationParam param) {
        if(param.getId() == null || param.getStatus() == null){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }

        bpoTransferStationMapper.updateStatus(param.getId(),param.getStatus());
        return Result.success();
    }

    public BpoTransferStation isExsitAreaRel(BpoTransferStationParam bpoTransferStationParam){
        Integer areaRelId = isExisiAreaId(bpoTransferStationParam);

        // 新增场地
        String type = bpoTransferStationParam.getType();
        String regionName = bpoTransferStationParam.getRegionName();
        String businessName = bpoTransferStationParam.getBusinessName();
        String name = bpoTransferStationParam.getTransferName();
        String code = bpoTransferStationParam.getTransferCode();
        String linkMan = bpoTransferStationParam.getLinkMan();
        String phone = bpoTransferStationParam.getPhone();
        String remark = bpoTransferStationParam.getRemark();
        BpoTransferStation bpoTransferStation = new BpoTransferStation();
        bpoTransferStation.setId(bpoTransferStationParam.getId());
        bpoTransferStation.setCityId(areaRelId);
        bpoTransferStation.setType(type);
        bpoTransferStation.setRegionName(regionName);
        bpoTransferStation.setBusinessName(businessName);
        bpoTransferStation.setName(name);
        bpoTransferStation.setCode(code);
        bpoTransferStation.setLinkMan(linkMan);
        bpoTransferStation.setPhone(phone);
        bpoTransferStation.setRemark(remark);
        bpoTransferStation.setStatus(bpoTransferStationParam.getStatus());
        return bpoTransferStation;
    }

    public Integer isExisiAreaId(BpoTransferStationParam bpoTransferStationParam){
        // 省id
        Integer provinceId = bpoTransferStationParam.getProvinceId();
        // 省名称
        String provinceName = bpoTransferStationParam.getProvinceName();
        // 城市id
        Integer cityId = bpoTransferStationParam.getCityId();
        // 城市名称
        String cityName = bpoTransferStationParam.getCityName();
        // 区id
        Integer districtId = bpoTransferStationParam.getDistrictId();
        // 区名称
        String districtName = bpoTransferStationParam.getDistrictName();
        if(provinceId == null || cityId == null || districtId == null){
            return null;
        }

        SysAreaRel sysAreaRel = new SysAreaRel();
        sysAreaRel.setProvinceId(provinceId);
        sysAreaRel.setProvinceName(provinceName);
        sysAreaRel.setCityId(cityId);
        sysAreaRel.setCityName(cityName);
        sysAreaRel.setDistrictId(districtId);
        sysAreaRel.setDistrictName(districtName);

        // 判断是否已存在
        Integer areaRelId = sysAreaRelMapper.isExsitAreaRel(sysAreaRel);
        if(areaRelId == null){
            sysAreaRelMapper.insertSelective(sysAreaRel);
            areaRelId = sysAreaRel.getId();
        }
        return areaRelId;
    }

    public boolean isExistCode(BpoTransferStation bpoTransferStation){
        boolean flag = false;
        BpoTransferStation Station = bpoTransferStationMapper.isExistCode(bpoTransferStation);
        if(Station != null){
            flag = true;
        }
        return flag;
    }
}
