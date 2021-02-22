package com.rz.iot.bpo.mina.model.param;

import lombok.Data;

/**
 * @program: bpo-preview
 * @description: 员工审核上传数据
 * @author: YangShun
 * @create: 2020-07-22 09:58
 **/
@Data
public class MinaAuditParam {
    private Integer id;
    //联系方式
    private String phone;
    //工号
    private String workNo;
    //所属公司id
    private Integer companyId;
    //所属部门
    private Integer deptId;
    //员工类型
    private Integer type;
    //所属项目
    private Integer projectId;
}
