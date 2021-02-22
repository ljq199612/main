package com.rz.iot.bpo.mina.controller;

import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.show.MinaPersonShow;
import com.rz.iot.bpo.mina.service.MinaPersonService;
import net.bytebuddy.description.field.FieldDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: bpo-preview
 * @description: 人员信息
 * @author: YangShun
 * @create: 2020-07-20 10:43
 **/
@RestController
@RequestMapping("minaPerson")
public class MinaPersonController extends BaseController {

    @Autowired
    private MinaPersonService personService;

    /**
     * 获取所有人员信息
     *
     * @param select 查询的姓名或者工号
     * @return
     */
    @GetMapping("findAll")
    public Result findAll(String select) {
        startPage ();
        List<MinaPersonShow> all = personService.findAll (new BaseEntity (), select);
        return getData (all);
    }

    /**
     * 人员详细信息
     *
     * @param id 人员id
     * @return
     */
    @GetMapping("getDetail")
    public Result getDetail(Integer id) {
        return personService.getDetail (id);
    }

    /**
     * 个人信息
     * @param id  个人id
     * @return
     */
    @GetMapping("getPerson")
    public Result getPerson(Integer id){
        return personService.getPerson(id);
    }

    /**
     * 头部信息
     * @param id 人员id
     * @return
     */
    @GetMapping("/getHead")
    public Result getHead(Integer id){
        return personService.getHead(id);
    }
}
