package com.rz.iot.bpo.mina.model.show;

import com.rz.iot.bpo.mina.model.MinaProject;
import com.rz.iot.bpo.model.BpoProject;
import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目列表show
 * 作者 : qin xian
 * 创建时间 : 2020/7/20 0020 16:07
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class MinaProjectListShow extends MinaProject{

    //中转场名称
    private String transferStationName;
    //中转场编号
    private String transferStationCode;

    //部门Id
    private Integer deptId;

    //部门信息
    private SysDeptShow sysDeptShow;

    //甲方公司名
    private String firstCompanyName;

    //供应商信息
    private List<MinaSupplierSimpleList> minaSupplierSimpleLists;

}