package com.rz.iot.bpo.mina.service.impl;

import com.rz.iot.bpo.common.constant.Constants;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.common.utils.ServletUtils;
import com.rz.iot.bpo.common.utils.ip.IpUtils;
import com.rz.iot.bpo.framework.manager.AsyncManager;
import com.rz.iot.bpo.framework.manager.factory.AsyncFactory;
import com.rz.iot.bpo.framework.security.LoginUser;
import com.rz.iot.bpo.framework.security.service.SysPermissionService;
import com.rz.iot.bpo.framework.security.service.TokenService;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.SysMenuMapper;
import com.rz.iot.bpo.mapper.SysUserMapper;
import com.rz.iot.bpo.mina.mapper.MinaPersonCenterMapper;
import com.rz.iot.bpo.mina.mapper.MinaPersonMapper;
import com.rz.iot.bpo.mina.mapper.MinaSysUserMapper;
import com.rz.iot.bpo.mina.model.MinaPerson;
import com.rz.iot.bpo.mina.model.MinaSysUser;
import com.rz.iot.bpo.mina.model.param.MinaLoginParm;
import com.rz.iot.bpo.mina.model.show.PersonCenterShow;
import com.rz.iot.bpo.mina.model.show.WorkExperienceShow;
import com.rz.iot.bpo.mina.service.MinaPersonCenterService;
import com.rz.iot.bpo.model.SysMenu;
import com.rz.iot.bpo.model.SysRole;
import com.rz.iot.bpo.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: bpo-preview
 * @description: 个人中心
 * @author: YangShun
 * @create: 2020-07-22 17:51
 **/
@Slf4j
@Service
public class MinaPersonCenterServiceImpl implements MinaPersonCenterService {

    @Autowired(required = false)
    private MinaPersonCenterMapper personCenterMapper;

    @Autowired(required = false)
    private MinaSysUserMapper sysUserMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private SysPermissionService permissionService;

    @Resource
    private TokenService tokenService;

    @Resource
    private SysUserMapper usermapper;

    @Resource
    private MinaPersonMapper minaPersonMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    private static final DateFormat FORMAT = new SimpleDateFormat ("yyyy-MM-dd");

    @Override
    public Result<PersonCenterShow> getLinkMode() {
        SysUser user = SecurityUtils.getLoginUser ().getUser ();
        PersonCenterShow linkMode = personCenterMapper.getLinkMode (user.getUserId ());
        Result<PersonCenterShow> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (linkMode);
        return result;
    }

    @Override
    public Result<List<WorkExperienceShow>> getWorkExperience() {
        SysUser user = SecurityUtils.getLoginUser ().getUser ();
        List<WorkExperienceShow> experience = personCenterMapper.getWorkExperience (user.getUserId ());
        //用于排序，把如今工作经历放首位
        List<WorkExperienceShow> backList = sorting (experience);
        Result<List<WorkExperienceShow>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (backList);
        return result;
    }

    @Override
    public Result changePassword(MinaLoginParm parm) {
        MinaLoginParm userById = personCenterMapper.getUserById (parm.getUserId ());
        if(parm.getOldPassword ()==null ){
            return Result.requestParamError ("未输入旧密码");
        }else if(!parm.equals (userById.getOldPassword ())){
            return Result.requestParamError ("旧密码输入错误");
        }
        MinaSysUser sysUser = new MinaSysUser ();
        sysUser.setUserId (userById.getUserId ());
        sysUser.setPassword (SecurityUtils.encryptPassword (parm.getPassword ()));
        sysUserMapper.updateByPrimaryKeySelective (sysUser);
        return Result.success ();
    }

    @Override
    public Result forgetPassword(MinaLoginParm parm) {
        return null;
    }

    @Override
    public Result login(String openId, MinaLoginParm param) {
        //判断是个人还是管理员
        Integer isAdmin = -1;
        LoginUser loginUser=null;
        MinaSysUser sysUser = sysUserMapper.selectByOpenid (openId);
        if(param!=null){
            List<MinaSysUser> minaSysUsers = sysUserMapper.selectByParams (param.getUserName ());
            if(minaSysUsers.size ()==0){
                return Result.requestParamError ("查询无此账号,请注册");
            }
            // 用户验证
            Authentication authentication = null;
            try {
                // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
                authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken (minaSysUsers.get (0).getUsername (), param.getPassword ()));
            } catch (InternalAuthenticationServiceException | BadCredentialsException e) {
                log.debug(ResultConstants.USERNAME_OR_PASSWORD_ERROR.getMessage());
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(param.getUserName(), Constants.LOGIN_FAIL, e.getMessage()));
                return Result.error(ResultConstants.USERNAME_OR_PASSWORD_ERROR);
            } catch (Exception e) {
                log.debug("登录失败");
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(param.getUserName(), Constants.LOGIN_FAIL, e.getMessage()));
                return Result.error(ResultConstants.LOGIN_ERROR);
            }
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(param.getUserName(), Constants.LOGIN_SUCCESS, "登录成功"));
            loginUser = (LoginUser) authentication.getPrincipal();
            Integer personId = sysUserMapper.selectPersonId (loginUser.getUser ().getUserId ());
            sysUserMapper.insertRelation (personId,openId);
        }else{
            SysUser user = usermapper.findByUsername(sysUser.getUsername ());
            if(sysUser==null){
                return Result.requestParamError ("查询无此账号,请注册");
            }else {
                loginUser = new LoginUser(user, permissionService.getMenuPermission(sysUser));
            }
        }
        // 生成token
        String token = tokenService.createToken(loginUser);
        String ipAddr = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.getUser().setLoginIp(ipAddr);
        loginUser.getUser().setLoginDate(new Date());
        usermapper.updateByPrimaryKey(loginUser.getUser());
        SysRole sysRole = loginUser.getUser ().getSysRole ();
        if("person".equals (sysRole.getRoleSort ())){
            isAdmin = 1;//代表个人账号
        }else{
            isAdmin = 2;//代表管理员账号
        }
        if(isAdmin == -1){
            return Result.requestParamError ("登录失败，请联系管理员");
        }

        return Result.buildResult(ResultConstants.REQUEST_SUCCESS.getCode(), "登录成功")
                .useMapParam()
                .addMapParam("token", token)
                .addMapParam ("isAdmin",isAdmin.toString ());
    }

    @Override
    public Result signUp(MinaPerson person) {
        person.setStatus (DictDataConstants.NOT_VERIFY_STATUS);
        minaPersonMapper.insertSelective (person);
        return Result.success ("注册成功,请等待审核分配账号");
    }

    @Override
    public Result getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();

        List<SysMenu> menus = null;
        if (loginUser.getUser().isAdmin()) {
            menus = sysMenuMapper.getAllMenu();
        } else {
            menus = sysMenuMapper.getMenuByUserId(user.getUserId());
        }
        user.setPermissions(this.makeMenus(menus));
        return Result.success(user);
    }

    private String toDealWithDate(Date date, Timestamp timestamp){
        String format = FORMAT.format (date);
        String format1 = null;
        if(timestamp==null){
            format1 = "如今";
        }else{
            format1 = FORMAT.format (timestamp);
        }
        String back = format+"~"+format1;
        return back;
    }

    //用于排序，把如今工作经历放首位,并处理数据
    private List<WorkExperienceShow> sorting(List<WorkExperienceShow> lists){
        List<WorkExperienceShow> backList = new ArrayList<> ();
        for (WorkExperienceShow list : lists) {
            if(list.getDimissiontime ()==null) {
                list.setTimeShow (toDealWithDate (list.getDimissiontime (),null));
                backList.add (list);
            }
        }
        for (WorkExperienceShow list : lists) {
            if(list.getDimissiontime ()!=null){
                list.setTimeShow (toDealWithDate (list.getDimissiontime (),list.getCreateTime ()));
                backList.add (list);
            }
        }
        return backList;
    }

    private List<SysMenu> makeMenus(List<SysMenu> sources) {
        List<SysMenu> res = new ArrayList<>();
        for (SysMenu source1 : sources) {
            if (source1.getParentId() == 0) {
                for (SysMenu source2 : sources) {
                    if (source2.getParentId().equals(source1.getMenuId())) {
                        source1.getChildren().add(source2);
                    }
                }
                res.add(source1);
            }
        }
        return res;
    }
}
