package com.whitecat.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whitecat.blog.service.ArticleService;
import com.whitecat.blog.entity.Article;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * Article前端控制器
 * @author liuzhexian
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     *获取Article列表页面
     */
    @GetMapping("/articles")
    public ResponseEntity index(@RequestParam(value = "pageNum", defaultValue = "0") Long pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        Page<Article> page = new Page<>(pageNum,pageSize);
        return new ResponseEntity(articleService.page(page,null), HttpStatus.OK);
    }

    /**
     *根据id查询Article
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id){
        return new ResponseEntity(articleService.getById(id),HttpStatus.OK);
    }

    /**
     *保存Article
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Article article){
        article.setGmtCreate(new Date());
        article.setGmtModified(new Date());
        return new ResponseEntity(articleService.save(article),HttpStatus.CREATED);
    }

    /**
     *更新Article
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Article article){
        article.setGmtModified(new Date());
        return new ResponseEntity(articleService.updateById(article),HttpStatus.NO_CONTENT);
    }

    /**
     *根据id删除Article
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id){
        articleService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
