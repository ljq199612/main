package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.PicUtils;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.SysCompanyRoleUserMapper;
import com.rz.iot.bpo.mapper.SysDeptMapper;
import com.rz.iot.bpo.mapper.SysOrgRoleMenuMapper;
import com.rz.iot.bpo.mapper.SysUserMapper;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.SysUserParam;
import com.rz.iot.bpo.model.show.SysUserDetail;
import com.rz.iot.bpo.model.show.SysUserShow;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import com.rz.iot.bpo.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 系统用户业务实现
 * 作者 : Rycony
 * 创建时间 : 2020/6/21 17:16
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private PicUtils picUtils;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysCompanyRoleUserMapper sysCompanyRoleUserMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysOrgRoleMenuMapper sysOrgRoleMenuMapper;

    /**
     * 查询所有用户-分页
     * @param sysUserParam 用户参数
     * @return
     */
    @Override
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD_USER,deptSubRelAlias = "dept_rel")
    public List<SysUserShow> findAll(SysUserParam sysUserParam) {
        List<SysUserShow> list = sysUserMapper.findAll(sysUserParam);

        if(list != null && list.size() > 0){
            for(SysUserShow sysUserShow : list){
                // 通过部门id查询出整个往上的组织架构
                Integer deptId = sysUserShow.getDeptId();

                SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);

                // 向上查询出组织架构
                SysDeptShow sysDeptShow = sysDeptMapper.upFindOrgByDeptId(deptId,sysDept.getIsTop());
                sysUserShow.setDeptShows(sysDeptShow);
            }

        }
        return list;
    }

    /**
     * 新增用户
     * @param sysUserParam 用户接收参数
     * @return
     */
    @Override
    @Transactional
    public Result insert(SysUserParam sysUserParam) {
        if(sysUserParam == null || StringUtils.isEmpty(sysUserParam.getUsername()) || StringUtils.isEmpty(sysUserParam.getPassword()) || sysUserParam.getOrgType() == null || sysUserParam.getRoleId() == null
        & sysUserParam.getCompanyId() == null
        ){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }
        // 判断用户名是否存在
        if(isExistUser(sysUserParam.getUsername()))
            return Result.error(ResultConstants.SYS_TYPE_SAME);

        // 系统用户
        SysUser sysUser = new SysUser();
        sysUser.setAvatar(sysUserParam.getAvatar());
        sysUser.setUsername(sysUserParam.getUsername());
        // 密码加密
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUserParam.getPassword()));
        sysUser.setRealName(sysUserParam.getRealName());
        sysUser.setEmail(sysUserParam.getEmail());
        sysUser.setPhone(sysUserParam.getPhone());
        sysUser.setBackupsPhone(sysUserParam.getBackupsPhone());
        sysUser.setStatus(sysUser.getStatus());

        // 新增用户
        sysUserMapper.insertSelective(sysUser);

        // 新增用户.组织.角色关联关系
        SysCompanyRoleUser sysCompanyRoleUser = new SysCompanyRoleUser();
        sysCompanyRoleUser.setCompanyId(sysUserParam.getCompanyId());
        sysCompanyRoleUser.setRoleId(sysUserParam.getRoleId());
        sysCompanyRoleUser.setUserId(sysUser.getUserId());
        sysCompanyRoleUserMapper.insertSelective(sysCompanyRoleUser);

        // 如果不存在组织类型与角色,菜单的关联关系,则新增.存在则不新增,
        OrgRoleMenuMethod(sysUserParam.getOrgType(),sysUserParam.getRoleId());


        // 新增用户与部门之间关联关系
        sysDeptMapper.addDeptUserRel(sysUser.getUserId(),sysUserParam.getDeptId());

        return Result.success("系统新增用户成功");
    }

    /**
     * 更新用户
     * @param sysUserParam 用户参数
     * @return
     */
    @Override
    public Result update(SysUserParam sysUserParam) {
        if(sysUserParam == null && StringUtils.isEmpty(sysUserParam.getUsername()) && StringUtils.isEmpty(sysUserParam.getPassword())){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }
        // 判断用户名是否存在
        if(isExistUserUpdate(sysUserParam.getUsername(),sysUserParam.getUserId()))
            return Result.error(ResultConstants.SYS_TYPE_SAME);
        // 系统用户
        SysUser sysUser = new SysUser();
        sysUser.setUserId(sysUserParam.getUserId());
        sysUser.setAvatar(sysUserParam.getAvatar());
        sysUser.setUsername(sysUserParam.getUsername());
        sysUser.setRealName(sysUserParam.getRealName());
        sysUser.setEmail(sysUserParam.getEmail());
        sysUser.setPhone(sysUserParam.getPhone());
        sysUser.setBackupsPhone(sysUserParam.getBackupsPhone());
        sysUser.setStatus(sysUser.getStatus());

        SysCompanyRoleUser sysCompanyRoleUser = new SysCompanyRoleUser();
        sysCompanyRoleUser.setCompanyId(sysUserParam.getCompanyId());
        sysCompanyRoleUser.setRoleId(sysUserParam.getRoleId());
        sysCompanyRoleUser.setUserId(sysUser.getUserId());
        sysCompanyRoleUserMapper.update(sysCompanyRoleUser);

        sysUserMapper.updateByPrimaryKeySelective(sysUser);

        // 修改用户与部门之间的关联关系
        if(sysUserParam.getUserId() != null && sysUserParam.getDeptId() != null){
            sysDeptMapper.updateDeptUserRel(sysUserParam.getUserId(),sysUserParam.getDeptId());
        }

        return Result.success("系统更新用户信息成功");
    }

    /**
     * 删除用户
     * @param sysUserParam 用户参数
     * @return
     */
    @Override
    public Result delete(SysUserParam sysUserParam) {
        if(sysUserParam == null && sysUserParam.getUserId() == null && sysUserParam.getUserId() == 0)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        sysUserParam.setStatus(DictDataConstants.DELETE_STATUS);

        // 删除用户
        SysUser sysUser = new SysUser();
        sysUser.setUserId(sysUserParam.getUserId());
        sysUser.setStatus(sysUserParam.getStatus());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);

        // 删除用户角色权限
        SysCompanyRoleUser sysCompanyRoleUser = new SysCompanyRoleUser();
        sysCompanyRoleUser.setStatus(DictDataConstants.DELETE_STATUS);
        sysCompanyRoleUser.setUserId(sysUser.getUserId());
        sysCompanyRoleUserMapper.update(sysCompanyRoleUser);
        return Result.success("系统删除用户成功");
    }

    /**
     * 上传图片文件
     * 需求:
     *      1.将文件存储至指定文件服务器
     *      2.修改文件名称保证文件的唯一性
     *      3.返回文件的url值
     * @param file 文件
     * @return
     */
    @Override
    public Result uploadPic(MultipartFile file) {
        if(file == null)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        // 获取文件名
        String fileName = file.getOriginalFilename();
        if(!PicUtils.isPic(fileName))
            return Result.error(null,"文件格式错误");
        MultipartFile[] multipartFiles = {file};
        // 上传图片
        Map<String,String> map = picUtils.upload(multipartFiles);
        return Result.success(map);
    }

    /**
     * 重置密码
     * @param sysUserParam 参数
     * @return
     */
    @Override
    public Result resetPwd(SysUserParam sysUserParam) {
        Integer userId = sysUserParam.getUserId();
        String password = sysUserParam.getPassword();
        if(StringUtils.checkPwd(password)){
            return Result.error("密码长度控制在6-16");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return Result.success("系统用户密码更新成功");
    }

    /**
     * 查询用户详情
     * @param sysUserParam
     * @return
     */
    @Override
    public Result detail(SysUserParam sysUserParam) {
        if(sysUserParam == null && sysUserParam.getUserId() == null)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        SysUserDetail sysUserDetail = sysUserMapper.detail(sysUserParam.getUserId());
        Integer deptId = sysUserDetail.getDeptId();
        if(deptId != null){
            SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);

            // 向上查询出组织架构
            SysDeptShow sysDeptShow = sysDeptMapper.upFindOrgByDeptId(deptId,sysDept.getIsTop());
            sysUserDetail.setSysDeptShow(sysDeptShow);
        }

        return Result.success(sysUserDetail);
    }

    /**
     * 删除文件
     * @param url 文件url
     * @return
     */
    @Override
    public Result deleteFile(String url) {
        if(url == null)
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        picUtils.delete(url);
        return Result.success();
    }

    /**
     * 更改用户状态
     * @param sysUserParam
     * @return
     */
    @Override
    public Result updateUserStatus(SysUserParam sysUserParam) {
        if(sysUserParam.getUserId() == null || sysUserParam.getStatus() == null){
            return Result.error(ResultConstants.REQUEST_PARAM_ERROR);
        }
        sysUserMapper.updateUserStatus(sysUserParam.getUserId(),sysUserParam.getStatus());
        return Result.success();
    }

    @Override
    public List<Map<String, Object>> selectAuditorByProject(Integer projectId) {
        return sysUserMapper.selectAuditorByProject(projectId);
    }

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return
     */
    private boolean isExistUser(String username) {
       boolean flag = false;
       SysUser sysUser = sysUserMapper.findByUsername(username);
       if(sysUser != null){
            flag = true;
       }
       return flag;
    }

    /**
     * 判断修改用户时用户名是否存在
     * @param username 用户名
     * @return
     */
    private boolean isExistUserUpdate(String username,Integer userId) {
        boolean flag = false;
        SysUser sysUser = sysUserMapper.isExistUserUpdate(username,userId);
        if(sysUser != null){
            flag = true;
        }
        return flag;
    }

    /**
     * 在新增和更新用户时,是否新增组织类型和角色以及菜单三者之间的关联关系
     */
    public void OrgRoleMenuMethod(Integer orgId,Integer roleId){
        SysOrgRoleMenu sysOrgRoleMenu = sysOrgRoleMenuMapper.findMenuRelByOrgRole(orgId,roleId);
        if(sysOrgRoleMenu == null){
            SysOrgRoleMenu newSysOrgRoleMenu = new SysOrgRoleMenu();
            newSysOrgRoleMenu.setRoleId(roleId);
            newSysOrgRoleMenu.setOrgId(orgId);
            sysOrgRoleMenuMapper.insertSelective(newSysOrgRoleMenu);
        }
    }
}
