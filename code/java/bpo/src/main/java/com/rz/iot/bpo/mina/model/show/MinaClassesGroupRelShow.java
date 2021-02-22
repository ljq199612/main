package com.rz.iot.bpo.mina.model.show;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.iot.bpo.mina.model.MinaClassesGroupRel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MinaClassesGroupRelShow extends MinaClassesGroupRel {

    // 工作小组名称
    private String workGroupName;

}