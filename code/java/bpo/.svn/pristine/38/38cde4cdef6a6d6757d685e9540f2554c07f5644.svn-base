package com.rz.iot.bpo.framework.manager.factory;

import com.rz.iot.bpo.common.constant.Constants;
import com.rz.iot.bpo.common.utils.LogUtils;
import com.rz.iot.bpo.common.utils.ServletUtils;
import com.rz.iot.bpo.common.utils.ip.AddressUtils;
import com.rz.iot.bpo.common.utils.ip.IpUtils;
import com.rz.iot.bpo.common.utils.spring.SpringUtils;
import com.rz.iot.bpo.mapper.SysLoginLogMapper;
import com.rz.iot.bpo.mapper.SysOperLogMapper;
import com.rz.iot.bpo.model.SysLoginLog;
import com.rz.iot.bpo.model.SysOperLog;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 * 
 * @author ruoyi
 */
public class AsyncFactory
{
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登陆信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
            final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLoginLog sysLoginLog = new SysLoginLog();
                sysLoginLog.setUserName(username);
                sysLoginLog.setIpaddr(ip);
                sysLoginLog.setLoginLocation(address);
                sysLoginLog.setBrowser(browser);
                sysLoginLog.setOs(os);
                sysLoginLog.setMsg(message);
                sysLoginLog.setLoginTime(new Date());

                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status))
                {
                    sysLoginLog.setStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    sysLoginLog.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SysLoginLogMapper.class).insertSelective(sysLoginLog);
            }
        };
    }

    /**
     * 操作日志记录
     * 
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(SysOperLogMapper.class).insertSelective(operLog);
            }
        };
    }
}
