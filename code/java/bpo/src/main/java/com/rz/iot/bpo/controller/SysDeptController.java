package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoTransferStation;
import com.rz.iot.bpo.model.SysDept;
import com.rz.iot.bpo.model.SysDeptSubRel;
import com.rz.iot.bpo.service.SysDeptService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 组织管理
 */
@RestController
@RequestMapping("sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 新增一个组织
     * @param dept
     * @return
     */
    @PostMapping("addDept")
    public Result addDept(SysDept dept){
        return sysDeptService.addDept (dept);
    }

    /**
     * 更新组织
     * @param dept
     * @return
     */
    @PostMapping("updateDept")
    public Result updateDept(SysDept dept){
        return sysDeptService.updateDept (dept);
    }

    /**
     * 删除一个组织
     * @param id
     * @return
     */
    @GetMapping("deleteDept")
    public Result deleteDept(Integer id){
        return sysDeptService.deleteDept (id);
    }

    /**
     * 提供一个接口用于获取user所属组织的组织架构
     * @return
     */
    @GetMapping("getAllByDUserId")
    public Result<List<SysDept>> getAllByDUserId(){
        return sysDeptService.getAllByUserId (new BaseEntity ());
    }

    /**
     *模糊查询
     * @return
     */
    @GetMapping("getCondition")
    public Result<Map<Integer, SysDept>> getCondition(@RequestParam(required = false ,value = "name")String name,
                                              @RequestParam(required = false,value = "status") Integer status){
        return sysDeptService.getCondition (new BaseEntity (),status,name);
    }

    /**
     * 获取用户的部门以及他的下级部门
     * @return
     */
    @GetMapping("getCanControl")
    public Result<List<SysDept>> getCanControl(){
        return sysDeptService.findCanControl (new BaseEntity ());
    }

    /**
     * 更新状态信息
     * @param dept
     * @return
     */
    @GetMapping("updateStatus")
    public Result updateStatus(SysDept dept){
        return sysDeptService.updateStatus (dept);
    }

    /**
     * 根据dept_id获取单条数据
     * @param deptId
     * @return
     */
    @GetMapping("getDeptByDeptId")
    public Result<SysDept> getDeptByDeptId(Integer deptId){
        return sysDeptService.getOneByDeptId (deptId);
    }


    /**
     * 通过部门id,获取向上所有部门单树信息如:通过深圳区Id获取 华东区/广东区/深圳区
     * @param deptId
     * @return
     */
    @GetMapping("/findDeptAll")
    public Result findDeptAll(Integer deptId){
        return sysDeptService.findDeptAll(deptId);
    }

}
