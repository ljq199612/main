package com.rz.iot.bpo.mina.service.impl;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.mapper.MinaPersonAuditMapper;
import com.rz.iot.bpo.mina.model.param.MinaAuditParam;
import com.rz.iot.bpo.mina.model.show.MinaDeptShow;
import com.rz.iot.bpo.mina.model.show.MinaPersonShow;
import com.rz.iot.bpo.mina.service.MinaPersonAuditService;
import com.rz.iot.bpo.model.BpoProject;
import com.rz.iot.bpo.model.SysUser;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: bpo-preview
 * @description: 人员审核管理
 * @author: YangShun
 * @create: 2020-07-20 10:57
 **/
@Service
public class MinaPersonAuditServiceImpl implements MinaPersonAuditService {

    @Autowired(required = false)
    private MinaPersonAuditMapper personAuditMapper;

    /**
     * 查询所有未审核的人员
     * @param entity
     * @param select
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "dept")
    public List<MinaPersonShow> findAll(BaseEntity entity, String select) {
        List<MinaPersonShow> all = personAuditMapper.findAll (entity, select);
        for (MinaPersonShow minaPersonShow : all) {
            String format = DateFormatUtils.format (minaPersonShow.getCreateTime (), "yyyy-MM-dd");
            String today = DateFormatUtils.format (new Date (), "yyyy-MM-dd");
            String today1 = DateFormatUtils.format (minaPersonShow.getCreateTime (), "hh:mm");
            if(today.equals (format)){
                minaPersonShow.setShowTime ("今天"+today1);
            }else{
                minaPersonShow.setShowTime (format);
            }
        }
        return all;
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Override
    public Result<MinaPersonShow> getDetail(Integer id) {
        MinaPersonShow detail = personAuditMapper.getDetail (id);
        Result<MinaPersonShow> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (detail);
        return result;
    }

    /**
     * 获取公司和部门信息
     * @param entity
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "dept")
    public Result<List<MinaDeptShow>> getDept(BaseEntity entity) {
        List<MinaDeptShow> dept = personAuditMapper.getDept (entity);
        List<MinaDeptShow> change = change (dept);
        Result<List<MinaDeptShow>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (change);
        return result.success (change);
    }

    /**
     * 获取项目信息
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "rel")
    public Result<List<BpoProject>> getProject(BaseEntity entity) {
        List<BpoProject> project = personAuditMapper.getProject (entity);
        Result<List<BpoProject>>  result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (project);
        return result;
    }

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "rel")
    public Result check(MinaAuditParam param) {
        personAuditMapper.updateParam (param);
        return Result.success ();
    }

    //选择顶级部门或最大的部门(适用于同一部门) 并返回递归后的数据
    private List<MinaDeptShow> change(List<MinaDeptShow> lists){
        //判断是否为超级管理员
        List<MinaDeptShow> minashows = new ArrayList<> ();
        SysUser loginUser = SecurityUtils.getLoginUser ().getUser ();
        if(loginUser.isAdmin()){
            List<MinaDeptShow> list = new ArrayList<> ();
            for (MinaDeptShow dept : lists) {
                if (dept.getIsTop () == 1) {
                    list.add (dept);
                }
            }
            superTrans (list, lists);
            return list;
        }else{
            //如果不是超级管理员
            MinaDeptShow top = lists.get (0);
            for (MinaDeptShow list : lists) {
                if(list.getDescendants ().length ()>top.getDescendants ().length ()){
                    top = list;
                }
            }
            lists.remove (top);
            trans (top,lists);
            minashows.add (top);

        }
        return minashows;
    }

    //子递归下级部门(超级管理员)
    private void superTrans(List<MinaDeptShow> top,List<MinaDeptShow> lists){
        List<MinaDeptShow> sysDepts = new ArrayList<> ();
        boolean goon = false;
        for (MinaDeptShow list : top) {
            for (MinaDeptShow dept : lists) {
                if (dept.getIsTop () == 0 && list.getDeptId () == dept.getParentId ()) {
                    list.getDeptShows ().add (dept);
                    sysDepts.add (dept);
                    goon = true;
                }
            }
        }
        lists.removeAll (sysDepts);
        if (goon == true) superTrans (sysDepts, lists);
        return;
    }

    //子递归下级部门(非超级管理员)
    private void trans(MinaDeptShow top,List<MinaDeptShow> lists){
        List<MinaDeptShow> list2 = new ArrayList<> ();
        for (MinaDeptShow list : lists) {
            if(list.getParentId ()==top.getDeptId ()){
                top.getDeptShows ().add (list);
                list2.add (list);
            }
        }
        if(list2.size ()==0) return;
        lists.removeAll (list2);
        for (MinaDeptShow minaDeptShow : list2) {
            trans (minaDeptShow,lists);
        }
    }
}
