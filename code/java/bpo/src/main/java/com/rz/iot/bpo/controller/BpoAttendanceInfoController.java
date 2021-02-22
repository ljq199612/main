package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.common.utils.ExcelUtil;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoAttendanceAuditRecord;
import com.rz.iot.bpo.model.BpoAttendanceInfo;
import com.rz.iot.bpo.model.param.BpoAttendanceInfoParam;
import com.rz.iot.bpo.model.show.BpoAttendanceInfoShow;
import com.rz.iot.bpo.service.BpoAttendanceInfoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 考勤信息
 */
@RestController
@RequestMapping("/attendanceInfo")
public class BpoAttendanceInfoController extends BaseController {

    @Resource
    private BpoAttendanceInfoService attendanceInfoService;

    /**
     * 查询考勤信息 分页
     * @param params
     * @return
     */
    @GetMapping("/getAll")
    public Result getAll(Map<String,Object> params) {
        startPage();
        List<BpoAttendanceInfoShow> list = attendanceInfoService.findAll(params);
        return getDataTable(list);
    }

    /**
     * 驻场审核、文员确认
     * @param attendanceInfoParam
     * @return
     */
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
//        Integer[] ids = params.get("ids");
        attendanceInfoService.oneKeyConfirm(ids,type);
        return Result.success();
    }

    /**
     * 打卡明细
     * @param attendanceInfoId
     * @return
     */
    @GetMapping("/clockDetail")
    public Result clockDetail(Integer attendanceInfoId) {
        List<Map<String,Object>> list = attendanceInfoService.clockDetail(attendanceInfoId);
        return Result.success(list);
    }

    /**
     * 审核记录
     * @param attendanceInfoId
     * @return
     */
    @GetMapping("/auditDetail")
    public Result auditDetail(Integer attendanceInfoId) {
        List<Map<String,Object>> list = attendanceInfoService.auditDetail(attendanceInfoId);
        return Result.success(list);
    }

    /**
     * 导出excel
     * @return
     */
    @RequestMapping("/export")
    public void export(Map<String,Object> params,HttpServletResponse response) throws Exception{
        List<BpoAttendanceInfoShow> list = attendanceInfoService.findAll(params);
        HSSFWorkbook wb = ExcelUtil.outAttendanceInfoBook(list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("考勤信息"+ LocalDate.now().toString(), "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
    }

}
