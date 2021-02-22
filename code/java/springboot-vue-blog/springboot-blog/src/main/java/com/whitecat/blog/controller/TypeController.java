package com.whitecat.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whitecat.blog.service.TypeService;
import com.whitecat.blog.entity.Type;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Type前端控制器
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/blog/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 获取Type列表页面
     */
    @GetMapping("/types")
    public ResponseEntity index(@RequestParam(value = "pageNum", defaultValue = "0") Long pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        Page<Type> page = new Page<>(pageNum, pageSize);
        return new ResponseEntity(typeService.page(page, null), HttpStatus.OK);
    }

    /**
     * 根据id查询Type
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        return new ResponseEntity(typeService.getById(id), HttpStatus.OK);
    }

    /**
     * 保存Type
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Type type) {
        type.setGmtCreate(new Date());
        type.setGmtModified(new Date());
        return new ResponseEntity(typeService.save(type), HttpStatus.CREATED);
    }

    /**
     * 更新Type
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Type type) {
        type.setGmtModified(new Date());
        return new ResponseEntity(typeService.updateById(type), HttpStatus.NO_CONTENT);
    }

    /**
     * 根据id删除Type
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        typeService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
