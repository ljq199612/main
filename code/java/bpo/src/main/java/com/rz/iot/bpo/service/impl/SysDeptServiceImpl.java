package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.BpoTransferStationMapper;
import com.rz.iot.bpo.mapper.SysDeptMapper;
import com.rz.iot.bpo.mapper.SysDictDataMapper;
import com.rz.iot.bpo.model.SysDept;
import com.rz.iot.bpo.model.SysDictData;
import com.rz.iot.bpo.model.SysUser;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import com.rz.iot.bpo.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired(required = false)
    private SysDeptMapper sysDeptMapper;

    @Autowired(required = false)
    private BpoTransferStationMapper bpoTransferStationMapper;

    @Autowired(required = false)
    private SysDictDataMapper sysDictDataMapper;



    @Override
    @Transactional
    public Result addDept(SysDept dept) {
        Result result = addHandle (dept);
        dept.setIsTop (0);
        if (result != null) return result;
        //获取上级部门
        SysDept dept1 = sysDeptMapper.selectByPrimaryKey (dept.getParentId ());
        dept.setCreateName (SecurityUtils.getLoginUser ().getUsername ());
        dept.setCreateTime (new Date ());
        dept.setTopId (dept1.getTopId ());
        sysDeptMapper.insertSelective (dept);

        String append = "," + dept.getDeptId ();
        sysDeptMapper.updateParent (dept.getParentId (), append);

        dept.setDescendants (dept.getDeptId ().toString ());
        sysDeptMapper.updateByPrimaryKeySelective (dept);
        return Result.success ();
    }

    @Override
    @Transactional
    public Result updateDept(SysDept dept) {
        if (dept.getDeptId () == null) {
            return Result.requestParamError ("部门id为空");
        }
        if (StringUtils.isEmpty (dept.getDeptName ())) {
            return Result.requestParamError ("部门名为空");
        }
        if (dept.getParentId () == null) {
            return Result.requestParamError ("上级部门id为空");
        }
        if (dept.getStatus () == null) {
            return Result.requestParamError ("状态信息为空");
        }
        SysDept dept1 = sysDeptMapper.selectByPrimaryKey (dept.getDeptId ());
        if(dept1.getStatus ()!=dept.getStatus ()){
            List<Integer> integers = sysDeptMapper.selectIsOrNotConnect (dept.getDeptId ());
            if (integers.size () != 0) return Result.requestParamError ("组织或下级组织已经被关联无法停用");
        }
        //判断是否修改了上级部门
        if (dept1.getParentId () == dept.getParentId ()) {
            sysDeptMapper.updateByPrimaryKeySelective (dept);
        } else {
            String[] split = dept1.getDescendants ().split (",");
            for (String s : split) {
                String append = "," + s;
                //删除原有的
                sysDeptMapper.updateAfterDelete (Integer.parseInt (s), append, dept1.getDeptId ());
                //新增新加的
                sysDeptMapper.updateParent (dept.getParentId (), append);
            }
            sysDeptMapper.updateByPrimaryKeySelective (dept);
        }

        return Result.success ();
    }

    @Override
    @Transactional
    public Result deleteDept(Integer id) {
        List<Integer> integers = sysDeptMapper.selectIsOrNotConnect (id);
        if (integers.size () != 0) return Result.requestParamError ("部门已经被绑定，无法删除");
        SysDept dept = sysDeptMapper.selectByPrimaryKey (id);
        String append = dept.getDescendants ();
        String[] split = append.split (",");
        List<SysDept> lists = new ArrayList<> ();
        SysDept dept1 = new SysDept ();
        dept1.setDeptId (id);
        dept1.setStatus (DictDataConstants.DELETE_STATUS);
        sysDeptMapper.updateByPrimaryKeySelective (dept1);
        for (String s : split) {
            SysDept dept2 = new SysDept ();
            dept2.setDeptId (Integer.parseInt (s));
            String name = "," + s;
            dept2.setDeptName (name);
            lists.add (dept2);
        }
        for (SysDept list : lists) {
            sysDeptMapper.updateAfterDelete (list.getDeptId (), list.getDeptName (), null);
        }

        return Result.success ();
    }

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias = "rel")
    public Result<List<SysDept>> getAllByUserId(BaseEntity entity) {
        List<SysDept> all = sysDeptMapper.findAll (entity,null,null);
        insertPriv (entity,all);
        Result<List<SysDept>> result = new Result (ResultConstants.REQUEST_SUCCESS);
        List<SysDept> sysDepts = changeDate (all);
        result.setData (sysDepts);
        return result;
    }

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD)
    public Result<List<SysDept>> findCanControl(BaseEntity entity) {
        //判断权限中是否含有顶级部门
        boolean istop = false;
        List<SysDept> sysDepts = null;
        List<SysDept> canControl = sysDeptMapper.findCanControl (entity);
        for (SysDept dept : canControl) {
            if(dept.getIsTop ()==1){
                istop = true;
            }
            dept.setControl (1);
        }
        if(istop){
            sysDepts = changeDate (canControl);
        }else {
            sysDepts = changeDate1 (canControl);
        }
        Result<List<SysDept>> result = new Result (ResultConstants.REQUEST_SUCCESS);
        result.setData (sysDepts);
        return result;
    }

    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD,deptSubRelAlias ="rel")
    public Result<Map<Integer, SysDept>> getCondition(BaseEntity entity,Integer status, String name) {
        Map<Integer,SysDept> map = new HashMap<> ();
        //查询不带条件的主体供应商的组织架构
        List<SysDept> all = sysDeptMapper.findAll (entity,null,null);
        //查询不带条件的下级供应商的组织架构
        List<SysDept> otherAll = sysDeptMapper.findAllOther (entity,null,null);
        if(StringUtils.isNotEmpty (name)||status!=null) {
            //查询带条件的主体供应商组织架构
            List<SysDept> sysDepts = sysDeptMapper.findAll (entity, status, name);
            //查询带条件的下级供应商的组织架构
            List<SysDept> deptLists = sysDeptMapper.findAllOther (entity,status,name);
            //去重存储上级组织
            Set<SysDept> back = new HashSet<> ();

            //便利查询到的list 判断all中descendants是否含有sysDepts的dept_id
            for (SysDept sysDept : all) {
                for (SysDept dept : sysDepts) {
                    //判断查到的dept_id的上级组织以及自身，并存入set
                    if (equals (sysDept,dept.getDeptId ())) {
                        back.add (sysDept);
                    }
                }
            }
            //存储主体供应商需要的上级组织
            List<SysDept> deptList = new ArrayList<> (back);
            back.removeAll (back);
            for (SysDept sysDept : otherAll) {
                for (SysDept dept : deptLists) {
                    //判断查到的dept_id的上级组织以及自身，并存入set
                    if (equals (sysDept,dept.getDeptId ())) {
                        back.add (sysDept);
                    }
                }
            }
            //存储下级供应商需要的上级组织
            List<SysDept> deptList1 = new ArrayList<> (back);

            //判断插入可操作的权限(超级管理员和其他管理员)
            if(entity.getUserId ()==null){
                insertPriv (entity,deptList);
                insertPriv (entity,deptList1);
            }else{
                insertPriv (entity,deptList);
            }

            //递归成树 （主体供应商）
            List<SysDept> sysDepts1 = changeDate (deptList);
            //递归成树 （下级供应商）
            List<SysDept> deptList2 = changeDate (deptList1);

            Result<Map<Integer, SysDept>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
            List<SysDept> last = new ArrayList<> ();
            last.addAll (sysDepts1);
            last.addAll (deptList2);
            //插入供应商入map
            for (int i = 1; i < last.size ()+1; i++) {
                map.put (i,last.get (i-1));
            }
            result.setData (map);
            return result;
        }else {
            if(entity.getUserId ()==null){
                insertPriv (entity,all);
                insertPriv (entity,otherAll);
            }else{
                insertPriv (entity,all);
            }
            List<SysDept> sysDepts = changeDate (all);
            List<SysDept> sysDepts1 = changeDate (otherAll);
            List<SysDept> last = new ArrayList<> ();
            last.addAll (sysDepts);
            last.addAll (sysDepts1);
            Result<Map<Integer, SysDept>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
            for (int i = 1; i < last.size ()+1; i++) {
                map.put (i,last.get (i-1));
            }
            result.setData (map);
            return result;
        }

    }

    @Override
    public Result updateStatus(SysDept dept) {
        List<Integer> integers = sysDeptMapper.selectIsOrNotConnect (dept.getDeptId ());
        if (integers.size () != 0) return Result.requestParamError ("组织或下级组织已经被关联无法停用");
        sysDeptMapper.updateStatus (dept);
        return Result.success ();
    }

    @Override
    public Result<SysDept> getOneByDeptId(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey (deptId);
        Result<SysDept> deptResult = new Result<> (ResultConstants.REQUEST_SUCCESS);
        deptResult.setData (dept);
        return deptResult;
    }

    @Override
    public Result<List<SysDictData>> findType() {
        List<SysDictData> allByType = sysDictDataMapper.findAllByType (DictDataConstants.CUSTOM_TYPE);
        Result<List<SysDictData>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (allByType);
        return result;
    }


    //插入可操作权限到list
    private void insertPriv(BaseEntity entity,List<SysDept> all) {
        if(entity.getUserId ()==null){
            for (SysDept sysDept : all) {
                sysDept.setControl (1);
            }
        }
        List<SysDept> canControl = sysDeptMapper.findCanControl (entity);
        for (SysDept datum : canControl) {
            for (SysDept dept : all) {
                if (datum.getDeptId () == dept.getDeptId () && dept.getIsTop () != 1) {
                    dept.setControl (1);
                }
            }
        }
    }

    //获取当前用户id
    private Integer getUserId() {
        SysUser user = SecurityUtils.getLoginUser ().getUser ();
        return user.getUserId ();
    }

    //特殊转换，用于将部分下级部门与top部门关联
    private List<SysDept> changeData(List<SysDept> depts) {
        List<SysDept> list = new ArrayList<> ();
        for (SysDept dept : depts) {
            if (dept.getIsTop () == 1) {
                list.add (dept);
            }
        }
        List<SysDept> lists = change1 (list, depts);
        return lists;
    }

    private List<SysDept> change1(List<SysDept> lists, List<SysDept> depts) {
        for (SysDept list : lists) {
            //存储顶级组织下所有的部门
            List<SysDept> sysDeptList = new ArrayList<> ();
            for (SysDept dept : depts) {
                if (dept.getIsTop () == 0 && list.getDeptId () == dept.getTopId ()) {
                    sysDeptList.add (dept);
                }
            }
            list.setDepts (sysDeptList);
            Integer length = sysDeptList.get (0).getDescendants ().length ();
            for (SysDept dept : sysDeptList) {
                if (dept.getDescendants ().length () > length) {
                    length = dept.getDescendants ().length ();
                }
            }
            for (SysDept dept : sysDeptList) {
                if (dept.getDescendants ().length () == length) {
                    dept.setIsTop (1);
                }
            }
            List<SysDept> sysDepts = changeDate (sysDeptList);
            list.setDepts (sysDepts);
        }
        return lists;
    }


    //转换有top的
    private List<SysDept> changeDate(List<SysDept> depts) {
        List<SysDept> list = new ArrayList<> ();
        for (SysDept dept : depts) {
            if (dept.getIsTop () == 1) {
                list.add (dept);
            }
        }
        change (list, depts);
        return list;
    }

    //转换不包含top的
    private List<SysDept> changeDate1(List<SysDept> depts) {
        List<SysDept> list = new ArrayList<> ();
        SysDept longist = depts.get (0);
        for (int i = 1; i < depts.size () - 1; i++) {
            if (longist.getDescendants ().length () < depts.get (i).getDescendants ().length ()) {
                longist = depts.get (i + 1);
            }
        }
        list.add (longist);
        change (list, depts);
        return list;
    }

    private void change(List<SysDept> lists, List<SysDept> depts) {
        List<SysDept> SysDepts = new ArrayList<> ();
        boolean goon = false;
        for (SysDept list : lists) {
            for (SysDept dept : depts) {
                if (dept.getIsTop () == 0 && list.getDeptId () == dept.getParentId ()) {
                    list.getDepts ().add (dept);
                    SysDepts.add (dept);
                    goon = true;
                }
            }
        }
        if (goon == true) change (SysDepts, depts);
        return;
    }

    private Result addHandle(SysDept dept) {
        if (dept.getParentId () == null) {
            Result.requestParamError ("父部门id为空");
        }
        if (dept.getDeptName () == null) {
            Result.requestParamError ("部门名为空");
        }
        if (dept.getTopId () == null) {
            Result.requestParamError ("顶级部门id为空");
        }
        return null;
    }

    //判断descendants是否含有dept_id
    private boolean equals(SysDept dept,Integer id){
        String[] split = dept.getDescendants ().split (",");
        for (String s : split) {
            if(s.equals (id.toString ())){
                return true;
            }
        }
        return false;
    }

    /**
     * 通过部门id,获取向上所有部门信息如:通过深圳区Id获取 华东区/广东区/深圳区
     * @param deptId
     * @return
     */
    @Override
    public Result findDeptAll(Integer deptId){


        //获取本部门详情
        SysDeptShow info = sysDeptMapper.findUpDept(deptId);

        //标记递归是否异常
        Integer temp = 0;


        if (info != null && info.getIsTop() == 0 && info.getParentId()!=null){
            info =  this.findUpDept(info,temp);
        }

        return Result.success(info);
    }

    @Override
    public SysDeptShow getDeptAll(Integer deptId){


        //获取本部门详情
        SysDeptShow info = sysDeptMapper.findUpDept(deptId);

        //标记递归是否异常
        Integer temp = 0;


        if (info != null && info.getIsTop() == 0 && info.getParentId()!=null){
            info =  this.findUpDept(info,temp);
        }

        return info;
    }

    //递归获取整个部门信息
    private SysDeptShow findUpDept(SysDeptShow downDept,Integer temp){
        //通过上级部门Id获取上级部门
        SysDeptShow info = sysDeptMapper.findUpDept(downDept.getParentId());
        List<SysDeptShow>list =  new ArrayList<>();
        list.add(downDept);
        info.setDeptShowList(list);

        //当存在上级部门时，获取上级部门 (当递归次数达到10次,判定数据问题导致递归异常,终止递归)
        if (info!=null && info.getIsTop() == 0 && info.getParentId()!=null && temp < 10){
            return this.findUpDept(info,temp+1);
        }
        else
            return info;
    }


}
