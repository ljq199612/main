package com.rz.iot.bpo.model.param;

import lombok.Data;

import java.util.List;

/**
 * 描述 : 创建项目添加项目供应商
 * 作者 : wangluyao
 * 创建时间 : 2020/6/22 19:41
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class ProjectSupplierParam {

    private Integer projectId;

    List<FirstLevelSupplierParam> firstLevelSupplierParamList;
}
