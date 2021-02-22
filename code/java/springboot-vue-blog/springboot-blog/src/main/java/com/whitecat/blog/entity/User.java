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
 * 博客用户
 * </p>
 *
 * @author liuzhexian
 * @since 2020-08-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_user")
public class User implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 编辑时间
     */
    private Date gmtModified;

    /**
     * 是否删除(0:否;1:是)
     */
    @TableLogic
    private Boolean deleted;


}
