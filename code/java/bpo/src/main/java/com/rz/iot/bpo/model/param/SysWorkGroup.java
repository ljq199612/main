package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoWorkGroup;
import com.rz.iot.bpo.model.SysWorkModuleRel;
import lombok.Data;

import java.util.List;
/**
* 描述 : 系统工序封装类
* 作者 : Rycony
* 创建时间 : 2020/6/17 19:12
*
* 修改原因 :
* 修改人 :
* 修改时间 ：
*/
@Data
public class SysWorkGroup {
    // id
    private Integer id;
    // 工序编码
    private String workGroupCode;
    // 工序名称
    private String workGroupName;
    // 父id
    private Integer parentId;
    // 类型
    private Integer type;
    // 工序集合
    private List<SysWorkModuleRel> processList;
}
