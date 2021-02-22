package com.whitecat.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whitecat.blog.service.UserService;
import com.whitecat.blog.entity.User;
import org.springframework.web.bind.annotation.RestController;

/**
 * User前端控制器
 * @author liuzhexian
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/blog/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *获取User列表页面
     */
    @GetMapping("/users")
    public ResponseEntity index(@RequestParam(value = "pageNum", defaultValue = "0") Long pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        Page<User> page = new Page<>(pageNum,pageSize);
        return new ResponseEntity(userService.page(page,null), HttpStatus.OK);
    }

    /**
     *根据id查询User
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id){
        return new ResponseEntity(userService.getById(id),HttpStatus.NO_CONTENT);
    }

    /**
     *保存User
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody User user){
        return new ResponseEntity(userService.save(user),HttpStatus.CREATED);
    }

    /**
     *更新User
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody User user){
        return new ResponseEntity(userService.updateById(user),HttpStatus.NO_CONTENT);
    }

    /**
     *根据id删除User
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id){
        userService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
