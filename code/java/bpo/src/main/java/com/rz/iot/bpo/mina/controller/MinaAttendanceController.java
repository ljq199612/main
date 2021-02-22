package com.rz.iot.bpo.mina.controller;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.utils.DateUtils;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.show.MinaAttendanceDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaAttendanceInfoShow;
import com.rz.iot.bpo.mina.service.MinaAttendanceInfoService;
import com.rz.iot.bpo.model.BpoAttendanceInfo;
import com.rz.iot.bpo.model.param.BpoAttendanceInfoParam;
import com.rz.iot.bpo.model.show.BpoAttendanceInfoShow;
import com.rz.iot.bpo.service.BpoAttendanceInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mp/attendance")
public class MinaAttendanceController extends BaseController {

    @Resource
    private MinaAttendanceInfoService infoService;

    @Resource
    private BpoAttendanceInfoService attendanceInfoService;


    @GetMapping("/findAll")
    public Result findAll(Map<String,Object> params) {
        startPage();
        List<MinaAttendanceInfoShow> list = infoService.findAll(params);
        return getDataTable(list);
    }

    @GetMapping("/detail")
    public Result detail(Integer id) {
        MinaAttendanceDetailShow detailShow = infoService.getDetail(id);
        return Result.success(detailShow);
    }

    @PostMapping("/confirm")
    public Result confirm(@RequestBody BpoAttendanceInfoParam attendanceInfoParam) {
        attendanceInfoService.confirm(attendanceInfoParam);
        return Result.success();
    }

    /**
     * 一键审核、确认
     * @param ids
     * @return
     */
    @PostMapping("/oneKeyConfirm")
    public Result oneKeyConfirm(@RequestParam Integer[] ids,@RequestParam Integer type) {
        attendanceInfoService.oneKeyConfirm(ids,type);
        return Result.success();
    }

    /**
     * 获取当前用户某一月的考勤信息
     */
    @GetMapping("/getListByMonth")
    public Result getListByMonth(@RequestParam int year,@RequestParam int month) {
        Date startTime = DateUtils.getBeginTime(year, month);
        Date endTime = DateUtils.getEndTime(year, month);
        List<BpoAttendanceInfoShow> list = infoService.getListByMonth(startTime,endTime);
        Map<String,Object> result = new HashMap<>(3);
        int abnormalCount = 0;
        for (BpoAttendanceInfoShow infoShow : list) {
            if (infoShow.getApprovalStatus() != null) {
                if (!infoShow.getApprovalStatus().equals(DictDataConstants.ATTENDANCE_NORMAL)) {
                    abnormalCount += 1;
                }
            } else {
                if (!infoShow.getRecordStatus().equals(DictDataConstants.ATTENDANCE_NORMAL)) {
                    abnormalCount +=1;
                }
            }
        }
        int total = list == null ? 0 : list.size();
        float abnormalRate = 100 - ((float) abnormalCount / total * 100);
        BigDecimal bd = new BigDecimal(abnormalRate);
        abnormalRate = bd.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
        result.put("total",total);
        result.put("abnormalCount",abnormalCount);
        result.put("normalRate",abnormalRate);
        result.put("data",list);
        return Result.success(result);
    }

    @GetMapping("/abnormalList")
    public Result abnormalList() {
        List<BpoAttendanceInfoShow> abnormalList = infoService.abnormalList();
        return Result.success(abnormalList);
    }

    /**
     * 申诉
     */
    @PostMapping("/complain")
    public Result complain(@RequestBody Map<String,Object> params) {
        infoService.complain(params);
        return Result.success();
    }

}
