package com.whitecat.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whitecat.blog.service.CommentService;
import com.whitecat.blog.entity.Comment;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment前端控制器
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/blog/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取Comment列表页面
     */
    @GetMapping("/comments")
    public ResponseEntity index(@RequestParam(value = "pageNum", defaultValue = "0") Long pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        Page<Comment> page = new Page<>(pageNum, pageSize);
        return new ResponseEntity(commentService.page(page, null), HttpStatus.OK);
    }

    /**
     * 根据id查询Comment
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        return new ResponseEntity(commentService.getById(id), HttpStatus.NO_CONTENT);
    }

    /**
     * 保存Comment
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Comment comment) {
        return new ResponseEntity(commentService.save(comment), HttpStatus.CREATED);
    }

    /**
     * 更新Comment
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Comment comment) {
        return new ResponseEntity(commentService.updateById(comment), HttpStatus.NO_CONTENT);
    }

    /**
     * 根据id删除Comment
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        commentService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
