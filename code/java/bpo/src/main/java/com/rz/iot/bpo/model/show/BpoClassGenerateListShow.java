package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoClassesGenerate;
import lombok.Data;

import java.util.List;

/**
 * 描述 : TODO
 * 作者 : 排班数据表Show，对前端展示
 * 创建时间 : 2020/7/29 0029 15:22
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoClassGenerateListShow extends BpoClassesGenerate {

    private String projectName;

    private String projectSn;

    private Integer transferStationId;

    private String transferStationName;

    private String transferStationCode;

    //实际安排人员数量
    private Integer personNumber;

    private List<BpoClassesGroupRelShow> bpoClassesGroupRelShows;

}