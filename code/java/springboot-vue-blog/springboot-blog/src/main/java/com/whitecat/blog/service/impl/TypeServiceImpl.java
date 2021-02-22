package com.whitecat.blog.service.impl;

import com.whitecat.blog.entity.Type;
import com.whitecat.blog.mapper.TypeMapper;
import com.whitecat.blog.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章类型表 服务实现类
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
