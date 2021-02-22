package com.whitecat.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 博客文章表
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_article")
public class Article implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章描述
     */
    private String description;

    /**
     * 文章作者id
     */
    private Long createdUserId;

    /**
     * 文章类型id
     */
    private Long typeId;

    /**
     * 文章标签id
     */
    private String tagId;

    /**
     * 文章内容(markerdown)
     */
    private String markerdownMessage;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 编辑时间
     */
    private Date gmtModified;

    /**
     * 是否发布(0:否;1:是)
     */
    private Boolean isRelease;

    /**
     * 是否删除(0:否;1:是)
     */
    @TableLogic
    private Boolean deleted;


}
