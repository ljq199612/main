package com.rz.iot.bpo.controller;

import com.ha.facecamera.configserver.pojo.AppConfig;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.common.utils.http.HttpUtils;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.BpoTransferStationParam;
import com.rz.iot.bpo.service.*;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 设备管理controller
 */
@Api(tags = "设备管理")
@RestController
@RequestMapping("equipment")
public class BpoAttendanceDeviceController {
    @Autowired
    private BpoAttendanceDeviceService bpoAttendanceDeviceService;

    @Autowired
    private BpoTransferStationService bpoTransferStationService;

    @Autowired
    private BpoAttendanceService bpoAttendanceService;

    @Autowired
    private SysCompanyService sysCompanyService;

    @Autowired
    private SysDeptService sysDeptService;

    //用于存储场地信息()
    private List<BpoTransferStation> transferStations = new ArrayList<> ();

    //用于存储公司信息()
    private List<SysCompany> sysCompanies = new ArrayList<> ();

    /**
     * 通过id获取设备的详细信息
     *
     * @param id 设备id
     * @return
     */
    @GetMapping("getDetail")
    public Result<BpoAttendanceDevice> getDetail(Integer id) {
        return bpoAttendanceDeviceService.getDetail (id);
    }



    /**
     * 更新一个设备信息
     *
     * @param equipment
     * @return
     */
    @PostMapping("updateEquipment")
    public Result updateEquipment(@RequestBody BpoAttendanceDevice equipment) {
        return bpoAttendanceDeviceService.updateEquipment (equipment);
    }

    /**
     * 删除一个设备信息
     *
     * @param id
     * @return
     */
    @GetMapping("deleteEquipment")
    public Result deleteEquipment(Integer id) {
        return bpoAttendanceDeviceService.deleteEquipment (id);
    }

    /**
     * 通过场地归属、场地名称、设备归属、设备网络状态去获取设备信息
     * @param deptId 部门id
     * @param transferStationId 场地id
     * @param deviceBelong 设备归属
     * @param deviceBelong 网络状态
     * @param page
     * @return
     */
    @GetMapping("getFuzzyDetails")
    public Result<List<BpoAttendanceDevice>> getFuzzyDetails(@RequestParam(value = "deptId" ,required = false)Integer deptId,
                                                             @RequestParam(value = "transferStationId",required = false)Integer transferStationId,
                                                             @RequestParam(value = "companyId" ,required = false)String deviceBelong,
                                                             @RequestParam(value = "networkStatus",required = false)Byte networkStatus,
                                                             Page<BpoAttendanceDevice> page) {
        BpoAttendanceDevice bpoAttendanceDevice = new BpoAttendanceDevice ();
        bpoAttendanceDevice.setTransferStationId (transferStationId);
        SysDept sysDept = new SysDept ();
        sysDept.setDeptId (deptId);
        bpoAttendanceDevice.setDeptId (deptId);
        bpoAttendanceDevice.setNetworkStatus (networkStatus);
        bpoAttendanceDevice.setAttendenceBelong (deviceBelong);

        return bpoAttendanceDeviceService.getFuzzyDetails (new BaseEntity (),bpoAttendanceDevice, page);
    }

    /**
     * 获取离线信息
     *
     * @param id
     * @return
     */
    @GetMapping("getOfflineInformation")
    public Result<String> getOfflineInformation(Integer id) {
        Result<String> offlineInformation = bpoAttendanceDeviceService.getOfflineInformation (id);
        return bpoAttendanceDeviceService.getOfflineInformation (id);
    }


    /**
     * 用于在获取到所有公司信息后进行快速查询
     * @param name 公司名
     * @return
     */
    @GetMapping("getCompany")
    public Result<List<SysCompany>> getCompany(String name){
        //用于存储返回后的数据
        List<SysCompany> list = new ArrayList<> ();
        if(sysCompanies!=null&&sysCompanies.size ()!=0){
            for (SysCompany sysCompany : sysCompanies) {
                String name1 = sysCompany.getCompanyName ();
                if(name1!=null && name1.contains (name)){
                    list.add (sysCompany);
                }
            }
        }
        Result<List<SysCompany>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (list);
        return result;
    }


    /**
     * 调用服务修改设备的配置信息
     * @param deviceNo
     * @param cloundConfigPort1
     * @param cloundConfigIp
     * @param dataUploadMethod1
     * @param workMode1
     * @return
     */
    @GetMapping("configEquipment")
    public Result<List<BpoAttendanceDevice>> configEquipment(@RequestParam("deviceNo")String deviceNo,
                                  @RequestParam(value = "cloundConfigPort",required = false) String cloundConfigPort1, //服务器端口
                                  @RequestParam(value = "cloundConfigIp",required = false) String cloundConfigIp, //服务器ip
                                  @RequestParam(value = "dataUploadMethod",required = false) String dataUploadMethod1, //数据上传方式
                                  @RequestParam(value = "workMode",required = false) String workMode1) {  //闸机模式
        Short cloundConfigPort=null;
        Short dataUploadMethod=null;
        Byte workMode=null;
        if(cloundConfigPort1!=null&&!"".equals(cloundConfigPort1)) {
            cloundConfigPort=Short.parseShort (cloundConfigPort1);
        }
        if(dataUploadMethod1!=null&&!"".equals(dataUploadMethod1)){
            dataUploadMethod=Short.parseShort (dataUploadMethod1);
        }
//        if(workMode1!=null&&!"".equals(workMode1)){
//            workMode=Byte.parseByte (workMode1);
//        }
        if(deviceNo==null){
            return Result.requestParamError ("设备序列号为空");
        }
        AppConfig appConfigByBySn = bpoAttendanceService.getAppConfigByBySn (deviceNo);
        if(appConfigByBySn==null){
            return Result.requestParamError ("获取devceNo为"+deviceNo+"的应用参数失败，设备未连接");
        }
        if(cloundConfigIp!=null && !"".equals(cloundConfigIp)){
            appConfigByBySn.setDataUploadServer (cloundConfigIp);
        }
        if(cloundConfigPort!=null ){
            appConfigByBySn.setDataUploadPort (cloundConfigPort);
        }
        if(dataUploadMethod!=null ){
            appConfigByBySn.setDataUploadMethod (dataUploadMethod);
        }

        //修改成功后会关闭连接无法返回
        bpoAttendanceService.setAppConfigBySn (appConfigByBySn.getDeviceNo (), appConfigByBySn, 5000);
        bpoAttendanceService.stopDeviceListener ();
        bpoAttendanceService.startDeviceListener ();
        //关闭原有监听然后用新端口重启监听
        List<String> list = new ArrayList<> ();
        list.add (deviceNo);
        Result<List<BpoAttendanceDevice>> afterUpdate = bpoAttendanceDeviceService.getAfterUpdate (list);
        return afterUpdate;
    }

    /**
     * 根据sn获取设备信息
     * @param sn
     * @return
     */
    @GetMapping("getAppConfig")
    public Result<AppConfig> getAppConfig(String sn){
        AppConfig appConfigByBySn = bpoAttendanceService.getAppConfigByBySn (sn);
        Result<AppConfig> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (appConfigByBySn);
        return result;
    }

    /**
     * 刷新选择信息
     * @return
     */
    @PostMapping("refresh")
    public Result<List<BpoAttendanceDevice>> refresh(@RequestBody Map map){
        boolean isture=true;
        List<String> selects = (List)map.get ("selects");
        if(selects==null||selects.size ()==0){
            return Result.requestParamError ("未选择需要更新的行");
        }
        for (String select : selects) {
            if(StringUtils.isEmpty (select)){
                isture=false;
            }
        }
        if(isture==false) return Result.requestParamError ("存在某行的设备序列号为空");

        return bpoAttendanceService.refresh (selects);
    }

    /**
     * 一键同步  向设备发送一个时间进行同步
     * @return
     */
    @PostMapping("synchronization")
    public Result<List<BpoAttendanceDevice>> synchronization(@RequestBody Map map){
        List<String> selects = (List)map.get ("selects");
        bpoAttendanceService.synchronization (selects);
        return bpoAttendanceDeviceService.getAfterUpdate (selects);
    }

    @GetMapping("getGateType")
    public Result<List<SysDictData>> getGateType(){
        return bpoAttendanceDeviceService.getGateType ();
    }

    @GetMapping("getUpLoad")
    public Result<List<SysDictData>> getUpLoad(){
        return bpoAttendanceDeviceService.getUpLoad ();
    }

    @GetMapping("getDept")
    public Result<List<SysDept>> getDept(){
        return sysDeptService.findCanControl (new BaseEntity ());
    }
}
