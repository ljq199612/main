package com.whitecat.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 博客评论表
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_comment")
public class Comment implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long pid;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 文章id
     */
    private Long articleId;

    private Date gmtCreate;

    /**
     * 是否删除(0:否;1:是)
     */
    @TableLogic
    private Boolean deleted;


}
