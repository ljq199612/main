package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.show.SysDataPerShow;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 数据详情show
 * 作者 : Rycony
 * 创建时间 : 2020/6/24 16:47
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysPerDetailShow {
    private Integer userId;
    private Integer orgId;
    private String orgName;
    private Integer roleId;
    private String roleName;
    private String username;

    // 部门
    private SysDeptShow sysDeptShow;
    // 拥有权限
    private List<SysDataPerShow> data;
    // 未拥有权限
    private List<SysDataPerShow> unData;
}
