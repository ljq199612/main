package com.rz.iot.bpo.service.impl;

import com.ha.facecamera.configserver.ConfigServer;
import com.ha.facecamera.configserver.ConfigServerConfig;
import com.ha.facecamera.configserver.DataServer;
import com.ha.facecamera.configserver.DataServerConfig;
import com.ha.facecamera.configserver.pojo.*;
import com.rz.iot.bpo.common.utils.DateUtils;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.BpoAttendanceDeviceMapper;
import com.rz.iot.bpo.mapper.BpoPersonMapper;
import com.rz.iot.bpo.model.BpoAttendanceDevice;
import com.rz.iot.bpo.model.BpoAttendanceRecord;
import com.rz.iot.bpo.model.BpoPerson;
import com.rz.iot.bpo.model.param.SysNation;
import com.rz.iot.bpo.service.BpoAttendanceDeviceService;
import com.rz.iot.bpo.service.BpoAttendanceRecordService;
import com.rz.iot.bpo.service.BpoAttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : 考勤设备接口 对设备接口
 * 作者 : qin xian
 * 创建时间 : 2020/6/24 0024 14:20
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service("bpoAttendanceService")
public class BpoAttendanceServiceImpl implements BpoAttendanceService {

    @Resource
    private BpoAttendanceDeviceMapper bpoAttendanceDeviceMapper;

    @Resource
    private BpoAttendanceRecordService attendanceRecordService;

    @Resource
    private BpoPersonMapper personMapper;

    @Resource
    private BpoAttendanceDeviceService attendanceDeviceService;

    @Resource
    private BpoPersonMapper bpoPersonMapper;


    private ConfigServer configServer = new ConfigServer();

    private DataServer dataServer = new DataServer();

    @Override
    public boolean startDeviceListener() {
        // TODO: 2020/6/26 0026 修改成从业务字段中获取（待定）
        Integer configServerPort = 10001;
        ConfigServerConfig csc = new ConfigServerConfig();
        //打印数据
        StringBuffer textArea = new StringBuffer();

        boolean ret = configServer.start(configServerPort, csc);
        if (ret) {
            textArea.append("启动配置服务器成功！");
            textArea.append("\n");
            configServer.onCameraConnected((val) -> {//val存储的是上报设备的Sn(序列号)
                //注册相机连接事件回调
                // TODO: 2020/6/17 0017 判断是否是第一次连接(设备Sn),判断该Sn是否存在
                textArea.append("设备已连接-配置端口 ");

                // TODO: 2020/6/26 0026 若是第一次连接,通过接口获取数据新增设备
                //客户端应用参数
                AppConfig configInfo =  configServer.getAppConfig(val);
                //版本信息
                Version Version = configServer.getVersion(val);


                //获取在线状态
                boolean status =  configServer.getCameraOnlineState(val);
                // TODO: 2020/6/26 0026 更新设备在线状态

                //设备校时(设置时间,将设备时间与服务器同步)
                configServer.setTime(val,new Time(),3000);

                //查询库中是否存在此设备
                BpoAttendanceDevice detailBySn = bpoAttendanceDeviceMapper.getDetailBySn (val);
                if(detailBySn==null){
                    //如果没有则新增
                    BpoAttendanceDevice change = change (configServer, Version,val);
                    bpoAttendanceDeviceMapper.insertSelective (change);
                }

              /*
                //打印数据
                textArea.append(val);
                textArea.append("\n\tAppConfig:\n\t\t");
                textArea.append(configServer.getAppConfig(val).toJson());
                textArea.append("\n\tVersion:\n\t\t");
                textArea.append(configServer.getVersion(val).toJson());
                textArea.append("\ntime" + t);

                System.out.println(textArea);*/

            });
            configServer.onCameraDisconnected((val) -> {
                //注册相机断开事件回调
                // TODO: 2020/6/17 0017 更新设备在线状态
                //断开更新离线时间
                BpoAttendanceDevice bpoAttendanceDevice=new BpoAttendanceDevice ();
                bpoAttendanceDevice.setNetworkStatus ((byte) 2);
                bpoAttendanceDevice.setDeviceSn (val);
                bpoAttendanceDevice.setUpdateTime (new Timestamp (System.currentTimeMillis ()));
                bpoAttendanceDeviceMapper.updateDisonConnect (bpoAttendanceDevice);


            });
            configServer.onAuthing((time, username, password) -> {
                // XXX 如果要校验用户名密码，请使用如下代码
                //return username.equals("xiaohe") && Util.judgeRegPassword(time, "123456", password);
                System.out.println(String.format("configServer 收到鉴权消息username=>%s password=>%s", username, new String(password)));
                return true;
            });

            configServer.onQrcode((text, sn) -> {

                System.out.println("二维码内容：" + text + ",sn" + sn);
                return true;
            });

            configServer.onTransparent((text, sn) -> {
                System.out.println("透明串口内容" + text);

            });

            return true;
        }
        return false;
    }


    @Override
    public void stopDeviceListener() {
        configServer.stop();
        configServer = new ConfigServer();
    }

    @Override
    public boolean startDateService() {
        Integer dateServicePort = 10002;
        DataServerConfig dsc = new DataServerConfig();

        //打印数据
        StringBuffer textArea = new StringBuffer();

        boolean ret = dataServer.start(dateServicePort, dsc);
        if (ret) {
            textArea.append("启动数据服务器成功！");
            textArea.append("\n");

            dataServer.onCameraConnected((val) -> {
                textArea.append("设备已连接-数据端口 ");
                textArea.append(val);
                textArea.append("\n");

                System.out.println(textArea);
            });
            dataServer.onCameraDisconnected((val) -> {
                textArea.append("设备已断开-数据端口 ");
                textArea.append(val);
                textArea.append("\n");
                System.out.println(textArea);
            });
            dataServer.onCaptureCompareDataReceived((data) -> {
                //注册对比抓拍数据接收事件回调
                textArea.append("收到抓拍对比数据\n\t");
                System.out.println("比对类型" + data.getMath_type() +
                        "是否实时" + data.isRealtime() +
                        "自定义字段" + data.getCustom() +
                        "伟根" + data.getWiegandNo1() +
                        "二维码类型" + data.getCodetype() +
                        "二维码" + data.getCodetext() +
                        ",体温" + data.getTemperature() +
                        ",口罩" + data.getIsMask());

                //抓拍数据
                CaptureCompareData ccDate = (CaptureCompareData) data.clone();
//                textArea.append(ccDate.toJson());
//                textArea.append("\n");
//                System.out.println(textArea);

                //身份证号 刷没刷身份证
                String idCard = ccDate.getIdCardNo();
                if (ccDate.getIdCardNo() != null) {
                    // TODO: 2020/6/17 0017 根据抓拍数判断该用户是否已经注册BPO
                    BpoPerson person = personMapper.selectByIdCard(idCard);
                    // TODO: 2020/6/17 0017 若未注册 则注册该用户（有效字段：身份证号+姓名.系统生成账户、密码与工号）到BPO,开始进行审核
                    // 解析对象中数据,获取新增用户需要的数据,进行新增,新增后状态为待审批
                    if (person == null) {
                        //查询所有民族信息
                        List<SysNation> nations = personMapper.getNations ();
                        person = new BpoPerson();
                        person.setPersonName(ccDate.getIdCardName());
                        person.setIdCard(ccDate.getIdCardNo());
                        person.setSex(Integer.valueOf(ccDate.getIdCardSex()));
                        for (SysNation nation : nations) {
                            if(ccDate.getIdCardNation ()!=null && ccDate.getIdCardNation ().equals (nation.getNation ())){
                                person.setNation (nation.getId ().toString ());
                            }
                        }
                        StringBuffer birth = new StringBuffer ();
                        birth.append (StringUtils.substring (ccDate.getIdCardBirth(),0,3)+"-");
                        birth.append (StringUtils.substring (ccDate.getIdCardBirth(),4,5)+"-");
                        birth.append (StringUtils.substring (ccDate.getIdCardBirth(),6,7));
                        person.setBirth(birth.toString ());
                        person.setIdCardAddress(ccDate.getIdCardAddress());
                        person.setStatus (0);
                        personMapper.insertSelective (person);
                        //工号
                    }

                } else {
                    idCard = ccDate.getPersonID();
                }

                // TODO: 2020/6/26 0026 存储抓拍记录到考勤记录表bpo_attendance_record
                attendanceRecordService.insertCaptureCompareData(ccDate);
                // 人员注册审核成功后（人员管理执行） 细节描述为：人员关联项目 执行执行以下步骤
                // TODO: 2020/6/26 0026 通过用户身份证Id从考勤记录表(bpo_attendance_record)中获取该用户第一次出现的数据(记录第一次出现在哪个设备),
                BpoAttendanceRecord attendanceRecord = attendanceRecordService.getLatestData(idCard);

                // TODO: 2020/6/26 0026 获取设备上该用户的图像信息(通过身份证Id可以精确查询出一条数据)
                Face[] faces =  this.listFaceBySn(attendanceRecord.getDeviceSn(), null,3000,idCard);
//                Face face = faces[0];
//                face.setId("232131131313");
                //设备编辑后,将设备关联到中转场.获取该中转场下所有的项目与考勤设备
                // TODO: 2020/6/26 0026  将该用户下发到中转场下所有的考勤机中
                if (faces.length > 0) {
                    List<String> deviceSns = bpoAttendanceDeviceMapper.getTransferStationDevSns(attendanceRecord.getDeviceSn());
                    for (int i = 0; i < deviceSns.size(); i++) {
                        configServer.addFaceBySn(deviceSns.get(i),faces[0],3000);
                    }
                }

            });
            dataServer.onAuthing((time, username, password) -> {
                // XXX 如果要校验用户名密码，请使用如下代码
                //return username.equals("xiaohe") && Util.judgeRegPassword(time, "123456", password);
                System.out.println(String.format("dataServer 收到鉴权消息username=>%s password=>%s", username, new String(password)));
                return true;
            });
            return true;
        }
        return false;
    }

    @Override
    public void stopDateService() {
        dataServer.stop();
        dataServer = new DataServer();
    }

    @Override
    public boolean getCameraOnlineState(String sn) {
        return configServer.getCameraOnlineState(sn);
    }

    @Override
    public AppConfig getAppConfigByBySn(String sn) {
        return configServer.getAppConfigByDeviceNo(sn);
    }

    @Override
    public Face[] listFaceBySn(String sn, ListFaceCriteria criteria, long timeoutInMilli ,String idCard) {
        /**
         * 示例
         */

        if(criteria == null){
            criteria=new ListFaceCriteria();
            criteria.setIseffective(true);
//            criteria.getRestrictions().expireDateBetween(Constants.LONGLIVE, Constants.LONGLIVE);
//

            //添加搜索参数 Id匹配
            criteria =  criteria.getRestrictions().idEq(idCard);
        }
        FacePage facePage = configServer.listFace(sn,criteria,timeoutInMilli);
        AppConfig appConfig = configServer.getAppConfig (sn);
        Face[] face = facePage.getFaces();

        return face;
    }

    @Override
    public boolean addFaceBySn(String sn, Face face, long timeoutInMilli) {
        return configServer.addFaceBySn(sn,face,3000);
    }

    //更新设备配置
    @Override
    public boolean setAppConfigBySn(String deviceNo, AppConfig appConfig, long timeoutInMilli) {
        boolean b = configServer.setAppConfigBySn (deviceNo, appConfig, timeoutInMilli);
        return b;
    }

    @Transactional
    //刷新设备的ip 参数是获取的deviceSn的String数组
    @Override
    public Result<List<BpoAttendanceDevice>> refresh(List<String> selects) {
        List<BpoAttendanceDevice> list = new ArrayList<> ();
        for (String select : selects) {
            AppConfig appConfigBySn = configServer.getAppConfigBySn (select);
            NetConfig netConfigBySn = configServer.getNetConfigBySn (select);
            if(appConfigBySn == null || appConfigBySn.getDeviceNo ()==null){
                continue;
            }
            BpoAttendanceDevice bpoAttendanceDevice=new BpoAttendanceDevice ();
            if(appConfigBySn!=null){
                if(StringUtils.isNotEmpty (appConfigBySn.getCloundConfigIp ())){
                    bpoAttendanceDevice.setIp (netConfigBySn.getIp ());
                }
                bpoAttendanceDevice.setNetworkStatus ((byte)1);
                bpoAttendanceDevice.setDeviceSn (select);
                bpoAttendanceDevice.setUpdateTime (new Timestamp (System.currentTimeMillis ()));
            }
            list.add (bpoAttendanceDevice);
        }
        for (BpoAttendanceDevice bpoAttendanceDevice : list) {
            bpoAttendanceDeviceMapper.updatefresh (bpoAttendanceDevice);
        }


        return attendanceDeviceService.getAfterUpdate(selects);
    }

    //一键同步
    @Override
    public Result<List<BpoAttendanceDevice>> synchronization(List<String> selects) {
        for (String sn : selects) {
            AppConfig appConfigBySn = configServer.getAppConfigBySn (sn);
            if(appConfigBySn==null){
                continue;
            }
            if(sn==null){
                return Result.requestParamError ("尝试以sn设置设备时间，但传入null");
            }
            Time timeBySn = configServer.getTimeBySn (sn, 5000);
            //获取当前系统的时间和日期
            if(timeBySn!=null) {
                String datePath = DateUtils.datePath ();
                String time = DateUtils.getTime ();
                String[] s1 = time.split (" ");
                String time1 = s1[1];
                //设置发送的时间
                timeBySn.setTime (time1);
                //设置发送的日期
                timeBySn.setDate (datePath);
                boolean b = configServer.setTimeBySn (sn, timeBySn, 5000);
                if(b==false){
                    return Result.requestParamError ("发送失败，请刷新设备");
                }
            }
        }
        return attendanceDeviceService.getAfterUpdate(selects);
    }

    //添加新设备
    @Override
    public Result addEquipment(String sn) {
        //客户端应用参数
        AppConfig configInfo =  configServer.getAppConfig(sn);
        //版本信息
        Version version = configServer.getVersion(sn);

        //获取在线状态
        boolean status =  configServer.getCameraOnlineState(sn);

        //设备校时(设置时间,将设备时间与服务器同步)
        configServer.setTime(sn,new Time(),3000);
        BpoAttendanceDevice changes = change (configServer, version,sn);
        //查询库中是否存在此设备
        BpoAttendanceDevice detailBySn = bpoAttendanceDeviceMapper.getDetailBySn (sn);
        if(detailBySn==null){
            //如果没有则新增
            BpoAttendanceDevice changea = change (configServer, version,sn);
            bpoAttendanceDeviceMapper.insertSelective (changes);
        }
        return Result.success ();
    }

    //转换数据
    public BpoAttendanceDevice change(ConfigServer configServer,Version version,String sn){
        BpoAttendanceDevice bpoAttendanceDevice=new BpoAttendanceDevice ();
        AppConfig appConfig = configServer.getAppConfig (sn);
        NetConfig netConfig = configServer.getNetConfig (sn);
        bpoAttendanceDevice.setDeviceNo (sn);
        bpoAttendanceDevice.setDeviceSn (sn);
        bpoAttendanceDevice.setStatus ((byte)1);
        bpoAttendanceDevice.setNetworkStatus ((byte)1);
        bpoAttendanceDevice.setIp (netConfig.getIp ());
        //闸机模式
        //暂无
        bpoAttendanceDevice.setVersion (version.getFirewareVersion ());
        return bpoAttendanceDevice;
    }
}