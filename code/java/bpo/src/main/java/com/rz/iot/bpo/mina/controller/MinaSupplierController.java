package com.rz.iot.bpo.mina.controller;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.show.MinaSupplierDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaSupplierListShow;
import com.rz.iot.bpo.mina.service.MinaSupplierService;
import com.rz.iot.bpo.model.show.SupplierDetailShow;
import com.rz.iot.bpo.model.show.SupplierListShow;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/minaSupplier")
public class MinaSupplierController {

    @Resource
    MinaSupplierService minaSupplierService;

    /**
     * 获取供应商列表
     * @param page
     * @param info
     * @return
     */
    @GetMapping("/list")
    public Result<List<MinaSupplierListShow>> list(Page<MinaSupplierListShow> page, MinaSupplierListShow info) {
        BaseEntity param = new BaseEntity();
        return minaSupplierService.list(param, info, page);
    }

    @ApiOperation("获取供应商详情")
    @GetMapping("/detail")
    public Result<SupplierDetailShow> detail(Integer id) {
        return minaSupplierService.detail(id);
    }
}
