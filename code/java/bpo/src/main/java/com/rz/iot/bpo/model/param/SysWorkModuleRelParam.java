package com.rz.iot.bpo.model.param;

import lombok.Data;

import java.util.List;

/**
 * @Description 工作模块/工序/工作小组接受前端参数实体类
 * @Author Rycony
 * @Date 2020/6/15 19:28
 */
@Data
public class SysWorkModuleRelParam {
    // id
    private Integer id;
    // 工作模块编码
    private String workModuleCode;
    // 工作模块名称
    private String workModuleName;
    // 父id
    private Integer parentId;
    // 类型
    private Integer type;
    private List<SysWorkGroup> sysWorkGroups;
}
