package com.rz.iot.bpo.framework.aspectj;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.common.utils.ServletUtils;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.common.utils.spring.SpringUtils;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.security.LoginUser;
import com.rz.iot.bpo.framework.security.service.TokenService;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 数据过滤处理
 * 
 * @author ruoyi
 */
@Aspect
@Component
public class DataScopeAspect
{
    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    /**
     * 用户部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD_USER = "5";


    // 配置织入点
    @Pointcut("@annotation(com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope)")
    public void dataScopePointCut()
    {
    }

    @Before("dataScopePointCut() && @annotation(dataScope)")
    public void doBefore(JoinPoint point, DataScope dataScope) throws Throwable
    {
        handleDataScope(point, dataScope);
    }

    protected void handleDataScope(final JoinPoint joinPoint, DataScope dataScope)
    {
        // 获取当前的用户
        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
        SysUser currentUser = loginUser.getUser();
        if (currentUser != null)
        {
            // 如果是超级管理员，则不过滤数据
            if (!currentUser.isAdmin())
            {
                dataScopeFilter(joinPoint, currentUser, dataScope);
            }
        }
    }

    /**
     * 数据范围过滤
     * 
     * @param joinPoint 切点
     * @param user 用户
     * @param dataScope 权限注解
     */
    public static void dataScopeFilter(JoinPoint joinPoint, SysUser user, DataScope dataScope)
    {
        StringBuilder sqlString = new StringBuilder();

        String level = dataScope.level();
        String deptSubRelAlias = dataScope.deptSubRelAlias();
        switch (level) {
            case DATA_SCOPE_ALL:
                break;
            case DATA_SCOPE_CUSTOM:
                break;
            case DATA_SCOPE_DEPT:
                sqlString.append(StringUtils.format(
                        "{}.dept_id = (SELECT dept.dept_id FROM sys_dept dept,sys_dept_user_rel dept_user_rel WHERE dept.status = {} AND dept_user_rel.status = {} AND dept_user_rel.user_id = {} AND dept_user_rel.dept_id = dept.dept_id)",
                        deptSubRelAlias,
                        DictDataConstants.NORMAL_STATUS,
                        deptSubRelAlias,
                        DictDataConstants.NORMAL_STATUS,
                        DictDataConstants.NORMAL_STATUS,
                        user.getUserId()));
                break;
            case DATA_SCOPE_DEPT_AND_CHILD:
                sqlString.append(StringUtils.format(
                        "FIND_IN_SET({}.dept_id, (SELECT dept.descendants FROM sys_dept dept,sys_dept_user_rel dept_user_rel WHERE dept.status = {} AND dept_user_rel.status = {} AND dept_user_rel.user_id = {} AND dept_user_rel.dept_id = dept.dept_id))",
                        deptSubRelAlias,
                        DictDataConstants.NORMAL_STATUS,
                        DictDataConstants.NORMAL_STATUS,
                        user.getUserId()));
                break;
            case DATA_SCOPE_DEPT_AND_CHILD_USER:
                sqlString.append(StringUtils.format(
                        "{}.status = {} AND FIND_IN_SET({}.dept_id, (SELECT dept.descendants FROM sys_dept dept,sys_dept_user_rel dept_user_rel WHERE dept.status = {} AND dept_user_rel.status = {} AND dept_user_rel.user_id = {} AND dept_user_rel.dept_id = dept.dept_id))",
                        deptSubRelAlias,
                        DictDataConstants.NORMAL_STATUS,
                        deptSubRelAlias,
                        DictDataConstants.NORMAL_STATUS,
                        DictDataConstants.NORMAL_STATUS,
                        user.getUserId()));
                break;

        }

        if (StringUtils.isNotBlank(sqlString.toString()))
        {
            BaseEntity baseEntity = (BaseEntity) joinPoint.getArgs()[0];
            baseEntity.setDataScope(sqlString.toString());
            baseEntity.setUserId (SecurityUtils.getLoginUser ().getUser ().getUserId ());
        }
    }
}
