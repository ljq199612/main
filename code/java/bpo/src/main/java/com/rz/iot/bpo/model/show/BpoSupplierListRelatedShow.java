package com.rz.iot.bpo.model.show;

import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目关联供应商
 * 作者 : wangluyao
 * 创建时间 : 2020/6/22 20:36
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoSupplierListRelatedShow {

    private Integer projectId;

    List<FirstLevelSupplierShow> firstLevelSupplierParamList;

}
