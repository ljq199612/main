package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysUser;
import com.rz.iot.bpo.model.param.SysUserParam;
import com.rz.iot.bpo.model.show.SysUserShow;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 系统用户业务接口
 * 作者 : Rycony
 * 创建时间 : 2020/6/21 17:15
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
public interface SysUserService {
    List<SysUserShow> findAll(SysUserParam sysUserParam);

    Result insert(SysUserParam sysUserParam);

    Result update(SysUserParam sysUserParam);

    Result delete(SysUserParam sysUserParam);


    Result uploadPic(MultipartFile file);

    Result resetPwd(SysUserParam sysUserParam);

    Result detail(SysUserParam sysUserParam);

    Result deleteFile(String url);

    Result updateUserStatus(SysUserParam sysUserParam);

    List<Map<String,Object>> selectAuditorByProject(Integer projectId);
}
