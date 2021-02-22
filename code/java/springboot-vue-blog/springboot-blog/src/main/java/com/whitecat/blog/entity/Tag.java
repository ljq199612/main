package com.whitecat.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 博客标签表
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_tag")
public class Tag implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 是否删除(0:否;1:是)
     */
    @TableLogic
    private Boolean deleted;


}
