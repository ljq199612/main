package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoSupplierInfo;
import com.rz.iot.bpo.model.SysCompany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 描述 :
 * 作者 : wangluyao
 * 创建时间 : 2020/6/23 9:57
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FirstLevelSupplierShow extends SysCompany {

    private Integer contractId;
    private String contractName;
    List<SecondLevelSupplierShow> secondLevelSupplierShows;
}
