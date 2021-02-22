package com.rz.iot.bpo.model.show.personClass;

import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目排班项目实体类
 * 作者 : Rycony
 * 创建时间 : 2020/7/30 9:20
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoProjectClass {
    // 项目id
    private Integer projectId;
    // 项目名称
    private String projectName;
    // 班次
    List<BpoClass> bpoClassList;
}
