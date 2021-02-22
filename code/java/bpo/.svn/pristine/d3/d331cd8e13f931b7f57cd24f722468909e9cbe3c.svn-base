package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.WorkflowNodeInstance;
import com.rz.iot.bpo.service.WorkflowInstanceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workflowInstance")
public class WorkflowInstanceController extends BaseController {

    @Resource
    private WorkflowInstanceService instanceService;

    @GetMapping("/processProgress")
    public Result processProgress(Integer infoId,Byte type) {
        List<WorkflowNodeInstance> list = instanceService.processProgress(infoId, type);
        return Result.success(list);
    }


    @PostMapping("/approve")
    @Log(title = "审核流程",businessType = BusinessType.UPDATE)
    public Result approve(@RequestBody Map<String,Object> params) {
        instanceService.approve(params);
        return Result.success();
    }

}
