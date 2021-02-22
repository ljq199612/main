package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoProject;
import lombok.Data;

/**
 * 描述 : 项目简易详情 (基础项目信息 中转场信息)
 * 作者 : qin xian
 * 创建时间 : 2020/7/19 0019 15:48
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoProjectSimpleList extends BpoProject {

    private String transferStationName;

    private String transferStationCode;

}