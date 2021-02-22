package com.rz.iot.bpo.common.utils;

import com.rz.iot.bpo.framework.web.domain.Server;
import com.rz.iot.bpo.mapper.SysSimpleSysInfoMapper;
import com.rz.iot.bpo.model.SysSimpleSysInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * 描述 : 定时获取系统信息，CPU 内存 硬盘等 存到数据库
 * CPU 使用/空闲  RAM 使用/空闲  ROM 物理总容量/物理使用/使用率
 * 作者 : ZXD
 * 创建时间 : 2020/7/1 15:39
 */

@Configuration
@EnableScheduling
@PropertySource(value = "classpath:/application.yml")
public class SysteminfoUtils {
    @Resource
    private SysSimpleSysInfoMapper sysSimpleSysInfoMapper;

    @Scheduled(cron ="${SysInfo}")
    public void SysInfo()throws Exception{
        Server server = new Server();
        server.copyTo();

        /**
         * 调用接口 得到 本地物理磁盘总容量/总已用/使用率
         */
        double[] localData = new double[server.getSysFiles().size()];
        double[] localuser = new double[server.getSysFiles().size()];
        for(int i=0; i<server.getSysFiles().size(); i++){
            if("Local".equals(server.getSysFiles().get(i).getTypeName().substring(0,5))){
                localData[i] = Double.valueOf(server.getSysFiles().get(i).getTotal().trim().replaceAll(" GB",""));
                localuser[i] = Double.valueOf(server.getSysFiles().get(i).getUsed().trim().replaceAll(" GB",""));
            }
        }
        double total = 0;
        double used = 0;
        for(int i =0; i<localData.length-1; i++){
            total += localData[i];
            used += localuser[i];
        }

        DecimalFormat df = new DecimalFormat("#0.00");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setNaN("0.00");
        df.setDecimalFormatSymbols(dfs);

        SysSimpleSysInfo sysSimpleSysInfo = new SysSimpleSysInfo();
        sysSimpleSysInfo.setCpuFree((double)Math.round(server.getCpu().getFree()*100)/100);
        sysSimpleSysInfo.setCpuUsed((double)Math.round(((100-server.getCpu().getFree())*100)/100));
        sysSimpleSysInfo.setRamFree((double)Math.round(((100-server.getMem().getUsage())*100)/100));
        sysSimpleSysInfo.setRamUsed((double)Math.round(server.getMem().getUsage()*100)/100);
        sysSimpleSysInfo.setRomTotal((double)Math.round(total));
        sysSimpleSysInfo.setRomUsed((double)Math.round(used));
        sysSimpleSysInfo.setRomUsage(Double.valueOf(df.format((used/total)*100)));
        if(null != sysSimpleSysInfo) sysSimpleSysInfoMapper.insertSelective(sysSimpleSysInfo);

//        System.out.println("CPU使用度：(使用)"+(double)Math.round(((100-server.getCpu().getFree())*100)/100)+"  (空闲)"+(double)Math.round(server.getCpu().getFree()*100)/100);
//        System.out.println("内存使用度：(使用)"+(double)Math.round(server.getMem().getUsage()*100)/100+"  (空闲)"+(double)Math.round(((100-server.getMem().getUsage())*100)/100));
//        System.out.println("总物理磁盘："+total+"GB"+" 已经使用："+used+"GB"+" 使用率："+df.format((used/total)*100)+"%");
//        System.out.println(sysSimpleSysInfo.toString());
    }
}
