package com.rz.iot.bpo.framework.task;

import com.rz.iot.bpo.common.constant.Constants;
import com.rz.iot.bpo.framework.redis.RedisCache;
import com.rz.iot.bpo.mapper.SysDictTypeMapper;
import com.rz.iot.bpo.model.SysDictData;
import com.rz.iot.bpo.model.SysDictType;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 项目启动时加载数据
 * 作者 : Rycony
 * 创建时间 : 2020/7/27 14:29
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Component
@Log4j2
public class InitData implements ApplicationRunner {
    @Resource
    private SysDictTypeMapper sysDictTypeMapper;
    @Resource
    private RedisCache redisCache;

    /**
     * 初始化字典表数据
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<SysDictType> list = sysDictTypeMapper.findAllNoPage();
        /**
         * redis存储格式
         * key:sys_dict_type
         * value:所有字典类型以及字典类型所有数据 key: 所有字典类型key
         *                                      value: 字典类型数据   key:字典数据value
         *                                                           value:字典数据lable
         */
        Map<String,Map<String,SysDictData>> resultMap = new HashMap<>();

        for(SysDictType sysDictType : list){
            // 字典数据
            Map<String,SysDictData> mapMap = new HashMap<>();
            // 根据字典类型查询相关数据
            List<SysDictData> dictData = sysDictTypeMapper.findDataByType(sysDictType.getDictType());
            for(SysDictData sysDictData : dictData){
                // 添加字典数据
                mapMap.put(sysDictData.getDictValue(),sysDictData);
            }
            resultMap.put(sysDictType.getDictType(),mapMap);
        }


        log.debug("初始化数据字典数据：" + resultMap);

        // 添加至缓存
        redisCache.setCacheMap(Constants.SYS_DICT_TYPE,resultMap);
    }
}
