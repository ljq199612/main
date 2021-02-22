package com.rz.iot.bpo.service.impl;

import com.github.pagehelper.PageHelper;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.ExcelUtil;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.SysLoginLogMapper;
import com.rz.iot.bpo.mapper.SysOperLogMapper;
import com.rz.iot.bpo.mapper.SysRoleMapper;
import com.rz.iot.bpo.mapper.SysSimpleSysInfoMapper;
import com.rz.iot.bpo.model.SysLoginLog;
import com.rz.iot.bpo.model.SysOperLog;
import com.rz.iot.bpo.model.SysRole;
import com.rz.iot.bpo.model.SysSimpleSysInfo;
import com.rz.iot.bpo.model.param.BpoOptLoginLogParam;
import com.rz.iot.bpo.model.show.SysLogShow;
import com.rz.iot.bpo.model.show.SysLoginLogShow;
import com.rz.iot.bpo.service.SysLogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述 : 系统日志管理业务实现
 * 作者 : Rycony
 * 创建时间 : 2020/6/19 16:18
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service
@Log4j2
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysOperLogMapper sysOperLogMapper;
    @Resource
    private SysLoginLogMapper sysLoginLogMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysSimpleSysInfoMapper sysSimpleSysInfoMapper;

    /**
     * 查询所有操作日志/分页
     * @param sysOperLog 操作日志参数
     * @return
     */
    @Override
    public List<SysOperLog> findAllOpt( BpoOptLoginLogParam sysOperLog) {
        List<SysOperLog> list = sysOperLogMapper.findAll(sysOperLog);
        return list;
    }

    @Override
    public List<SysLoginLogShow> findAllLogin(BpoOptLoginLogParam sysLoginLog) {
        List<SysLoginLogShow> list = sysLoginLogMapper.findAll(sysLoginLog);
        return list;
    }

    @Override
    public Result findOptDetail(SysOperLog sysOperLog) {
        if(sysOperLog == null || sysOperLog.getOperId() == null){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }
        SysOperLog sysOperLog1 = sysOperLogMapper.selectByPrimaryKey(sysOperLog.getOperId());
        return Result.success(sysOperLog1);
    }

    /**
     * 获取系统用户角色
     * @return
     */
    @Override
    public Result findUserRole() {
        List<SysRole> list = sysRoleMapper.findAllRole();
        return Result.success(list);
    }

    /**
     * 导出系统操作日志
     * @return
     */
    @Override
    public Result exportOpeLog(HttpServletResponse response) {
        // 定义下载路径
        // 获取今日时间
        LocalDate localDate = LocalDate.now();


        try(OutputStream out = response.getOutputStream()) {

            String fileName = new String(("系统操作日志"+localDate.toString())
                    .getBytes(), "UTF-8");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx"));

            List<SysOperLog> list = sysOperLogMapper.findOpeLog();


            ExcelUtil.writeExl(out, list, 3, 5000, SysOperLog.class);

            out.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return Result.success();
    }

    /**
     * 导出登录日志
     * @return
     */
    @Override
    public Result exportLoginLog(HttpServletResponse response) {
        // 定义下载路径
        // 获取今日时间
        LocalDate localDate = LocalDate.now();


        try(OutputStream out = response.getOutputStream()) {

            String fileName = new String(("系统登录日志"+ localDate.toString())
                    .getBytes(), "UTF-8");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx"));

            List<SysLoginLogShow> list =  sysLoginLogMapper.findLoginLog();


            ExcelUtil.writeExl(out, list, 3, 5000, SysLoginLogShow.class);

            out.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return Result.success();
    }

    /**
     * 获取系统信息（CPU RAM ROM 整点上报 一小时一次）
     * @param sysLoginLog 时间参数（开始时间 结束时间）
     * @return
     */
    @Override
    public Result findSysInfo(BpoOptLoginLogParam sysLoginLog) {
        List<SysLogShow> list = sysSimpleSysInfoMapper.findSysInfo(sysLoginLog);

        for(SysLogShow sysLogShow : list){
            sysLogShow.setRomFree(100 - (sysLogShow.getRomUsed() == null?0 : sysLogShow.getRomUsed()));
        }
//        if(null ==sysLoginLog.getType()){
//            return Result.success(list);
//        }else if("cpu".equals(sysLoginLog.getType())){
//            List<SysSimpleSysInfo> cpu = list.stream().map(temp->{return new SysSimpleSysInfo(temp.getCpuUsed(), temp.getCpuFree(), 0.0,0.0, 0.0,0.0,0.0);}).collect(Collectors.toList());
//            return Result.success(cpu);
//        }else if("ram".equals(sysLoginLog.getType())){
//            List<SysSimpleSysInfo> ram = list.stream().map(temp->{return new SysSimpleSysInfo(0.0,0.0,temp.getRamUsed(), temp.getRamFree(), 0.0,0.0,0.0);}).collect(Collectors.toList());
//            return Result.success(ram);
//        }else if("rom".equals(sysLoginLog.getType())){
//            List<SysSimpleSysInfo> rom = list.stream().map(temp->{return new SysSimpleSysInfo(0.0,0.0,0.0,0.0, temp.getRomTotal(), temp.getRomUsed(), temp.getRomUsage());}).collect(Collectors.toList());
//            return Result.success(rom);
//        }
        return Result.success(list);
    }
}
