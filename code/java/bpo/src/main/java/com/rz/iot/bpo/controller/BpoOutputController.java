package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.BpoOutputEmployeeParam;
import com.rz.iot.bpo.model.param.BpoOutputParam;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputDetailEmployeeShow;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputDetailShow;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputShow;
import com.rz.iot.bpo.service.BpoOutputService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/output")
public class BpoOutputController extends BaseController {

    @Resource
    private BpoOutputService outputService;

    @PostMapping("/add")
    public Result add(@RequestBody BpoOutputParam outputParam) {
        outputService.add(outputParam);
        return Result.success();
    }

    @GetMapping("/getOutputList")
    public Result getOutputList(@RequestParam Map<String,Object> params) {
//        startPage();
        List<BpoOutputShow> outputShow = outputService.getOutputList(params);
        return Result.success(outputShow);
//        return getDataTable(outputShow);
    }

    @GetMapping("/getOutputDetailList")
    public Result getOutputDetailList(@RequestParam Integer id) {
        List<BpoOutputDetailShow> outputDetailShow = outputService.getOutputDetail(id);
        return Result.success(outputDetailShow);
    }

    @PostMapping("/toEmployeeOutput")
    public Result toEmployeeOutput(List<BpoOutputEmployeeParam> outputEmployeeParamList) {
        outputService.toEmployeeOutput(outputEmployeeParamList);
        return Result.success();
    }

    @GetMapping("/getEmployeeDetailList")
    public Result getEmployeeDetailList(Map<String,Object> params) {
        List<BpoOutputDetailEmployeeShow> list = outputService.getEmployeeDetailList(params);
        return Result.success(list);
    }

}
