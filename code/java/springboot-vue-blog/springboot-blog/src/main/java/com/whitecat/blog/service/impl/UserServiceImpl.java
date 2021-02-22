package com.whitecat.blog.service.impl;

import com.whitecat.blog.entity.User;
import com.whitecat.blog.mapper.UserMapper;
import com.whitecat.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客用户 服务实现类
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
