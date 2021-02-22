package com.rz.iot.bpo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ha.facecamera.configserver.ConfigServer;
import com.ha.facecamera.configserver.pojo.AppConfig;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.DateUtils;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.security.LoginUser;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.BpoAttendanceDeviceMapper;
import com.rz.iot.bpo.mapper.SysDeptMapper;
import com.rz.iot.bpo.mapper.SysDictDataMapper;
import com.rz.iot.bpo.mapper.SysDictTypeMapper;
import com.rz.iot.bpo.model.BpoAttendanceDevice;
import com.rz.iot.bpo.model.SysDept;
import com.rz.iot.bpo.model.SysDictData;
import com.rz.iot.bpo.model.SysUser;
import com.rz.iot.bpo.service.BpoAttendanceDeviceService;
import com.rz.iot.bpo.service.BpoAttendanceService;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BpoAttendanceDeviceServiceImpl implements BpoAttendanceDeviceService {
    @Resource
    private BpoAttendanceDeviceMapper bpoAttendanceDeviceMapper;

    @Resource
    private SysDictDataMapper sysDictDataMapper;

    @Resource
    private SysDeptMapper sysDept;

    //通过id获取设备的详细信息
    @Override
    public Result<BpoAttendanceDevice> getDetail(Integer id) {
        if (id == null) {
            return Result.requestParamError ("设备管理的id为空");
        }
        BpoAttendanceDevice bpoAttendanceDevice = bpoAttendanceDeviceMapper.selectByPrimaryKey (id);
        String name = getName (bpoAttendanceDevice.getDept ());
        bpoAttendanceDevice.setTransBelong (name);
        Result<BpoAttendanceDevice> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (bpoAttendanceDevice);
        return result;
    }

    //更新一个设备信息
    @Override
    public Result updateEquipment(BpoAttendanceDevice equipment) {
        if (equipment.getId () == null) {
            return Result.requestParamError ("设备id为空，请重试");
        }
        if (equipment.getTransferStationId () == null) {
            return Result.requestParamError ("场地id为空，请重试");
        }
        if (equipment.getDeviceSn () == null) {
            return Result.requestParamError ("设备序列号为空，请重试");
        }
        equipment.setUpdateTime (new Timestamp (System.currentTimeMillis ()));
        bpoAttendanceDeviceMapper.updateByPrimaryKeySelective (equipment);
        return Result.success ();
    }

    //删除一个设备信息
    @Override
    public Result deleteEquipment(Integer id) {
        if (id == null) {
            return Result.requestParamError ("设备id为空，请重试");
        }
        BpoAttendanceDevice device = new BpoAttendanceDevice ();
        device.setId (id);
        device.setStatus ((byte)DictDataConstants.DELETE_STATUS);
        bpoAttendanceDeviceMapper.updateByPrimaryKeySelective (device);
        return Result.success ();
    }

    /**
     * 通过场地归属、场地名称、设备归属、设备网络状态去获取设备信息
     *
     * @param device
     * @param page
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD, deptSubRelAlias = "rel")
    public Result<List<BpoAttendanceDevice>> getFuzzyDetails(BaseEntity param, BpoAttendanceDevice device, Page<BpoAttendanceDevice> page) {
        PageHelper.startPage (page.getPageNum (), page.getPageSize ());
        device.setUserId (getUserId ());
        if (device.getNetworkStatus () != null && device.getNetworkStatus () == 0) {
            device.setNetworkStatus (null);
        }
        List<BpoAttendanceDevice> fuzzyDetails = bpoAttendanceDeviceMapper.getFuzzyDetails (param, device);
        for (BpoAttendanceDevice fuzzyDetail : fuzzyDetails) {
            String name = getName (fuzzyDetail.getDept ());
            fuzzyDetail.setTransBelong (name);
        }
        PageInfo<BpoAttendanceDevice> pageInfo = new PageInfo<> (fuzzyDetails);
        page.setList (fuzzyDetails);
        page.setTotal (pageInfo.getTotal ());
        page.setPages (pageInfo.getPages ());
        Result<List<BpoAttendanceDevice>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        return result.success (page);
    }

    //获取离线信息
    @Override
    public Result<String> getOfflineInformation(Integer id) {
        if (id == null) {
            return Result.requestParamError ("设备id为空，请重试");
        }
        BpoAttendanceDevice offlineInformation = bpoAttendanceDeviceMapper.getOfflineInformation (id);
        Date updateTime = offlineInformation.getUpdateTime ();
        DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd hh:mm;ss");
        String format = dateFormat.format (updateTime);
        StringBuffer message = new StringBuffer ();
        message.append ("设备: ");
        message.append (offlineInformation.getDeviceName ());
        message.append ("于");
        message.append (format.split (" ")[0]);
        message.append ("离线");
        Result<String> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (message.toString ());
        return result;
    }

    @Override
    public Result<List<SysDictData>> getGateType() {
        List<SysDictData> allByType = sysDictDataMapper.findAllByType (DictDataConstants.GATE_MODEL);
        Result<List<SysDictData>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allByType);
        return result;
    }

    @Override
    public Result<List<SysDictData>> getUpLoad() {
        List<SysDictData> allByType = sysDictDataMapper.findAllByType (DictDataConstants.UPLOAD_MODEL);
        Result<List<SysDictData>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allByType);
        return result;
    }

    @Override
    public Result<List<BpoAttendanceDevice>> getAfterUpdate(List<String> deviceSn) {
        List<BpoAttendanceDevice> afterUpdate = bpoAttendanceDeviceMapper.getAfterUpdate (deviceSn);
        for (BpoAttendanceDevice bpoAttendanceDevice : afterUpdate) {
            bpoAttendanceDevice.setTransBelong (getName (bpoAttendanceDevice.getDept ()));
        }
        Result<List<BpoAttendanceDevice>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (afterUpdate);
        result.setMessage ("成功");
        return result;
    }


    //新增信息的判定
    private Result handleAddEquipmentParm(BpoAttendanceDevice device) {
        if (device.getTransferStationId () == null) {
            return Result.requestParamError ("设备所属场地未填写");
        }
        if (device.getDeviceSn () == null) {
            return Result.requestParamError ("设备序列号未填写");
        }
        return null;
    }

    //将组织进行拼接
    private String getName(List<SysDept> depts) {
        SysDept[] depts1 = new SysDept[depts.size ()];
        depts.toArray (depts1);
        StringBuffer back = new StringBuffer ();
        for (int i = 0; i < depts.size () - 1; i++) {
            for (int j = 0; j < depts.size () - i - 1; j++) {
                if (depts1[j].getDescendants ().length () < depts1[j + 1].getDescendants ().length ()) {
                    SysDept dept = depts1[j];
                    depts1[j] = depts1[j + 1];
                    depts1[j + 1] = dept;
                }
            }
        }
        for (int i = 0; i < depts1.length; i++) {
            if (i != depts1.length - 1) {
                back.append (depts1[i].getDeptName () + "/");
            } else {
                back.append (depts1[i].getDeptName ());
            }
        }
        return back.toString ();
    }

    private Integer getUserId() {
        LoginUser loginUser = SecurityUtils.getLoginUser ();
        return loginUser.getUser ().getUserId ();
    }
}
