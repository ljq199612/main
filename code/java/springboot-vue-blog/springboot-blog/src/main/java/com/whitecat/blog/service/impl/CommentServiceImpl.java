package com.whitecat.blog.service.impl;

import com.whitecat.blog.entity.Comment;
import com.whitecat.blog.mapper.CommentMapper;
import com.whitecat.blog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客评论表 服务实现类
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
