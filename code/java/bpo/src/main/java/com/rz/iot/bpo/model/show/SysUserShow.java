package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import lombok.Data;

/**
 * 描述 : 展示前端用户管理实体类
 * 作者 : Rycony
 * 创建时间 : 2020/6/21 17:17
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysUserShow {
    private Integer id;
    private String username;
    private String orgName;
    private String companyName;
    private Integer companyId;
    private String roleName;
    private Integer roleId;
    private String phone;
    private String status;
    private SysDeptShow deptShows;
    private Integer deptId;
}
