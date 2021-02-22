package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.show.personClass.BpoClass;
import com.rz.iot.bpo.model.show.personClass.BpoPersonArrangeParam;
import com.rz.iot.bpo.model.show.personClass.BpoPersonArrangeShow;
import com.rz.iot.bpo.service.BpoClassesGenerateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 人员安排Controller
 * 作者 : Rycony
 * 创建时间 : 2020/7/29 11:45
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@RestController
@RequestMapping("/bpoPersonArrange")
public class BpoPersonArrangeController extends BaseController {
    @Resource
    private BpoClassesGenerateService bpoClassesGenerateService;

    /**
     * 项目排班情况
     *  当前:没有排班情况下,不展示项目以及场地内容
     * @Param stationId 场地id
     * @Param projectId 项目id
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(Integer stationId,Integer projectId){
        startPage();
        List<BpoPersonArrangeShow> list = bpoClassesGenerateService.findAll(new BaseEntity(),stationId,projectId);
        return getData(list);
    }

    /**
     * 获取所有场地信息
     */
    @RequestMapping("/findStations")
    public Result findStations(){
        return bpoClassesGenerateService.findStations(new BaseEntity());
    }

    /**
     * 根据场地获取所有项目信息
     */
    @RequestMapping("/findProjects")
    public Result findProjects(Integer stationId){
        return bpoClassesGenerateService.findProjects(stationId);
    }

    /**
     * 人员安排详情
     * @param classId
     * @return
     */
    @RequestMapping("/personArrangeDetail")
    public Result findArrangeDetail(Integer classId){
        return bpoClassesGenerateService.findArrangeDetail(classId);
    }

    /**
     * 排班人员完成
     * @param param 请求参数
     */
    @RequestMapping("/arrangeFinish")
    public Result arrangeFinish(BpoPersonArrangeParam param){
       return bpoClassesGenerateService.finish(param);
    }
}
