package com.rz.iot.bpo.mina.controller;

import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.show.MinaProjectDetailAllShow;
import com.rz.iot.bpo.mina.model.show.MinaProjectDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaProjectListShow;
import com.rz.iot.bpo.mina.service.MinaProjectService;
import com.rz.iot.bpo.model.show.BpoProjectDetailAllShow;
import com.rz.iot.bpo.model.show.BpoProjectDetailShow;
import com.rz.iot.bpo.model.show.BpoProjectListShow;
import com.rz.iot.bpo.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/minaProject")
public class MinaProjectController {

    @Resource
    MinaProjectService minaProjectService;

    @Resource
    ProjectService projectService;

    /**
     * 获取项目列表
     * @param param
     * @return
     */
    @GetMapping("/list")
    public Result getList(MinaProjectListShow param, Page<MinaProjectListShow> page){
        return minaProjectService.getList(param, page);
    }

    /**
     * 获取项目详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result<MinaProjectDetailShow> detail(int id) {
        return minaProjectService.detail(id);
    }

    @ApiOperation("获取项目详情全部")
    @GetMapping("/detailAll")
//    @PreAuthorize("@ss.hasPermi('project:detailAll')")
//    @Log(title = "获取项目详情", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<MinaProjectDetailAllShow> detailAll(int id) {

        return minaProjectService.detailAll(id);
    }

}
