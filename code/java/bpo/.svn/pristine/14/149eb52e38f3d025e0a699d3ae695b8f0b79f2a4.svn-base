package com.rz.iot.bpo.mina.model.show;

import com.rz.iot.bpo.model.SysCompany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bpo-preview
 * @description: 展示用户所属组织信息
 * @author: YangShun
 * @create: 2020-07-20 19:12
 **/
@Data
public class MinaDeptShow {
    //部门id
    private Integer deptId;
    //部门名
    private String deptName;
    //公司信息
    private SysCompany company;
    //下属部门
    private List<MinaDeptShow> deptShows = new ArrayList<> ();
    //父部门
    private Integer parentId;
    //子集列表
    private String descendants;
    //是否为顶级部门
    private Integer isTop;
}
