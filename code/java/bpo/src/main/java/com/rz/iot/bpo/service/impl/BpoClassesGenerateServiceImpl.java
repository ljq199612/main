package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.BpoClassesGenerate;
import com.rz.iot.bpo.model.BpoProject;
import com.rz.iot.bpo.model.BpoTransferStation;
import com.rz.iot.bpo.model.show.personClass.*;
import com.rz.iot.bpo.service.BpoClassesGenerateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 人员安排业务实现
 * 作者 : Rycony
 * 创建时间 : 2020/7/29 11:52
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service
public class BpoClassesGenerateServiceImpl implements BpoClassesGenerateService {
    @Resource
    private BpoClassesGenerateMapper mapper;
    @Resource
    private BpoTransferStationMapper stationMapper;
    @Resource
    private BpoProjectMapper projectMapper;

    @Override
    @DataScope(deptSubRelAlias = "rel")
    public List<BpoPersonArrangeShow> findAll(BaseEntity baseEntity,Integer stationId,Integer projectId) {
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();

        List<BpoPersonArrangeShow> list =  mapper.findAll(baseEntity,stationId,projectId,userId);
        return list;
    }

    /**
     * 查询所有场地信息
     * @return
     */
    @Override
    @DataScope(deptSubRelAlias = "dept_sub_rel")
    public Result findStations(BaseEntity baseEntity) {
        List<BpoTransferStation> list = stationMapper.findAllByLoginUserId(baseEntity);
        return Result.success(list);
    }

    /**
     * 根据场地对应项目信息
     * @param stationId 场地id
     * @return
     */
    @Override
    public Result findProjects(Integer stationId) {
        // 获取当前用户
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();

        // 获取项目信息
        List<Map<String,Object>> list = projectMapper.findProjectByStationId(userId,stationId);
        return Result.success(list);
    }

    /**
     * 人员安排详情
     * @param classId
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD_USER,deptSubRelAlias = "dept_user_rel")
    public Result findArrangeDetail(Integer classId) {
        // todo 未做数据权限问题

        if(classId == null){
            return Result.error(ResultConstants.REQUEST_SUCCESS);
        }

        // 定义结果集
        BpoPersonArrageDetail bpoClassDetail = new BpoPersonArrageDetail();

        // 获取班次详情
        BpoClassesGenerate bpoClassesGenerate = mapper.selectByPrimaryKey(classId);
        bpoClassDetail.setClassId(bpoClassesGenerate.getId());
        bpoClassDetail.setClassSn(bpoClassesGenerate.getClassSn());
        bpoClassDetail.setClassType(bpoClassesGenerate.getClassType());
        // 日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 时间格式
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:ss:mm");

        bpoClassDetail.setStartTime(timeFormat.format(bpoClassesGenerate.getStartTime()));
        bpoClassDetail.setEndTime(timeFormat.format(bpoClassesGenerate.getEndTime()));
        bpoClassDetail.setStartDate(dateFormat.format(bpoClassesGenerate.getStartDate()));
        bpoClassDetail.setEndDate(dateFormat.format(bpoClassesGenerate.getEndDate()));

        // 扣除时薪
        long arriveTime = bpoClassesGenerate.getArriveTime().getTime();
        long startTime = bpoClassesGenerate.getStartTime().getTime();
        long endTime = bpoClassesGenerate.getEndTime().getTime();

        // 毫秒值转化为小时
        int nt = 1000*60*60;
        int time = (int) (startTime - arriveTime);
        int absTime = Math.abs(time);
        int excludeHour = absTime%nt == 0?absTime/nt:absTime/nt + 1;

        bpoClassDetail.setExcludeHour(excludeHour);

        int tempTime = (int) (endTime - arriveTime);
        int absTempTime = Math.abs(tempTime);
        int feeHour = absTempTime/nt;

        // 计薪
        bpoClassDetail.setFeeHour(feeHour);


        // 通过班次获取工作小组
        List<BpoGroupShow> groups = mapper.getGroupsByClassId(classId);
        bpoClassDetail.setWorkGroupShowList(groups);

        // 通过班次获取无工作小组人员
        List<BpoPersonShow> personShows = mapper.getPersonNoGroup(classId);
        bpoClassDetail.setBpoPersonShow(personShows);

        // 获取所有未添加人员
        List<BpoPersonShow> unPersonShows = mapper.getPersonNoClass();
        bpoClassDetail.setUnBpoPersonShow(unPersonShows);

        return Result.success(bpoClassDetail);
    }

    /**
     * 排班完成
     * @param param 请求参数
     * @return
     */
    @Override
    @Transactional
    public Result finish(BpoPersonArrangeParam param) {
        if(param.getClassId() == null){
            return Result.error(ResultConstants.REQUEST_SUCCESS);
        }

        // 定义班次信息
        BpoClassesGenerate bpoClassesGenerate = new BpoClassesGenerate();

        // 班次id
        Integer classId = param.getClassId();
        // 班次sn
        String classSn = param.getClassSn();
        // 班次分类
        Integer classType = param.getClassType();
        // 上班时间
        String startTime = param.getStartTime();
        // 下班时间
        String endTime = param.getEndTime();
        // 开始日期
        String startDate = param.getStartDate();
        // 结束日期
        String endDate = param.getEndDate();
        // 扣除时长
        Integer excludeHour = param.getExcludeHour();
        // 计薪时长,通过到达时间和下班时间差计算出来的
        // Integer feeHour = param.getFeeHour();
        // 人员安排日期
        String cycle = param.getCycle();

        // 日期格式
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 注入封装
        bpoClassesGenerate.setId(classId);
        bpoClassesGenerate.setClassSn(classSn);
        bpoClassesGenerate.setClassType(classType);

        Date cycleDate = null;
        try {
            bpoClassesGenerate.setStartTime(timeFormat.parse(startTime));
            bpoClassesGenerate.setEndTime(timeFormat.parse(endTime));
            bpoClassesGenerate.setStartDate(dateFormat.parse(startDate));
            bpoClassesGenerate.setEndDate(dateFormat.parse(endDate));
            cycleDate = dateFormat.parse(cycle);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bpoClassesGenerate.setExcludeHour(excludeHour);
        mapper.updateByPrimaryKeySelective(bpoClassesGenerate);

        // 所有新增数据处理
        // 已排班人员
        List<BpoGroupShow> bpoGroupShows = param.getClassList();

        // 新增排班人员,即未排班人员减少
        mapper.insertClassPerson(classId,cycleDate,bpoGroupShows);


        // 所有删除数据处理
        // 已删除排班人员,即未排班人员新增
        List<BpoPersonShow> delClassList = param.getDelClassList();
        mapper.updateStatusByPersonId(delClassList);

        return Result.success();
    }
}
