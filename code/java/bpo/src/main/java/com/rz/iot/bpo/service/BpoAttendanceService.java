package com.rz.iot.bpo.service;

import com.ha.facecamera.configserver.pojo.AppConfig;
import com.ha.facecamera.configserver.pojo.Face;
import com.ha.facecamera.configserver.pojo.ListFaceCriteria;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoAttendanceDevice;

import java.util.List;

/**
 * 描述 : 与考勤机硬件接口 Service
 * 作者 : qin xian
 * 创建时间 : 2020/6/24 0024 11:51
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
public interface BpoAttendanceService {

    /**
     * 启动设备监听
     *
     */
    boolean startDeviceListener();


    /**
     * 停止设备监听
     */
    void stopDeviceListener();

    /**
     * 启动数据监听
     */
    boolean startDateService();

    /**
     * 停止数据监听
     */
    void stopDateService();

    /**
     * 通过设备标识符/设备编号 获取设备在线状态
     * @param sn
     * @return
     */
    boolean getCameraOnlineState(String sn);

    /**
     * 通过设备编号获取客户端应用参数
     * @param sn
     * @return
     */
    AppConfig getAppConfigByBySn(String sn);

    /**
     * 分页查询人脸特征值数据
     *
     * @param sn       设备序列号
     * @param criteria       分页参数
     * @param timeoutInMilli 超时时间
     * @param idCard 身份证号
     * @return 查询到的特征值数据数组
     */
    Face[] listFaceBySn(String sn, ListFaceCriteria criteria, long timeoutInMilli ,String idCard);

    /**
     * 添加一个人脸到设备模板库中
     *
     * @param sn             设备序列号
     * @param face           人脸数据
     * @param timeoutInMilli 超时时间
     * @return 是否添加成功
     */
    boolean addFaceBySn(String sn, Face face, long timeoutInMilli);

    /**
     * 更新设备应用参数
     *
     * @param deviceNo 设备编号
     * @param appConfig 新的应用参数
     * @param timeoutInMilli 超时时间
     * @return 是否设置成功
     */
    boolean setAppConfigBySn(String deviceNo, AppConfig appConfig, long timeoutInMilli);

    /**
     * 刷新设备信息ip，在线状态
     * @param selects（需要修改的deviceSn）
     * @return
     */
    Result<List<BpoAttendanceDevice>> refresh(List<String> selects);

    /**
     * 一键同步
     * @return
     */
    Result<List<BpoAttendanceDevice>> synchronization(List<String> selects);

    /**
     * 设备注册被监听到，判断数据库中是否有此设备，没有则添加
     * @param sn 设备序列号
     * @return
     */
    Result addEquipment(String sn);
}