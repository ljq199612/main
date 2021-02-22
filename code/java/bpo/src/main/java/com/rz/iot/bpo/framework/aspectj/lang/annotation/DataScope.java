package com.rz.iot.bpo.framework.aspectj.lang.annotation;

import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;

import java.lang.annotation.*;

/**
 * 描述 : 数据权限过滤注解
 * 作者 : xuxiake
 * 创建时间 : 2020/7/3 16:42
 *
 * 修改原因 :
 * 修改人 : xuxiake
 * 修改时间 ：2020/7/3 16:42
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 权限级别
     */
    public String level() default DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD;
    /**
     * 部门下属关系表表的别名
     */
    public String deptSubRelAlias() default "dept_sub_rel";
}
