package com.whitecat.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whitecat.blog.service.TagService;
import com.whitecat.blog.entity.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * Tag前端控制器
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/blog/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 获取Tag列表页面
     */
    @GetMapping("/tags")
    public ResponseEntity index(@RequestParam(value = "pageNum", defaultValue = "0") Long pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        Page<Tag> page = new Page<>(pageNum, pageSize);
        return new ResponseEntity(tagService.page(page, null), HttpStatus.OK);
    }

    /**
     * 根据id查询Tag
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        return new ResponseEntity(tagService.getById(id), HttpStatus.OK);
    }

    /**
     * 保存Tag
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Tag tag) {
        return new ResponseEntity(tagService.save(tag), HttpStatus.CREATED);
    }

    /**
     * 更新Tag
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Tag tag) {
        return new ResponseEntity(tagService.updateById(tag), HttpStatus.NO_CONTENT);
    }

    /**
     * 根据id删除Tag
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        tagService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
