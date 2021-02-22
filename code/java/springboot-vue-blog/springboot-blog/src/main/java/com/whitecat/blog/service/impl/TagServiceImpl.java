package com.whitecat.blog.service.impl;

import com.whitecat.blog.entity.Tag;
import com.whitecat.blog.mapper.TagMapper;
import com.whitecat.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客标签表 服务实现类
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
