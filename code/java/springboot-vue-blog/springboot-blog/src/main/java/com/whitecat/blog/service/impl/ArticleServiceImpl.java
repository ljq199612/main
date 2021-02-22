package com.whitecat.blog.service.impl;

import com.whitecat.blog.entity.Article;
import com.whitecat.blog.mapper.ArticleMapper;
import com.whitecat.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
