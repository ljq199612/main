package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoClassesGroupRel;
import lombok.Data;

/**
 * 描述 : TODO
 * 作者 : 工作小组show，对前端展示
 * 创建时间 : 2020/7/29 0029 15:23
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoClassesGroupRelShow extends BpoClassesGroupRel{

    //工作小组名称
    private String workGroupName;

}