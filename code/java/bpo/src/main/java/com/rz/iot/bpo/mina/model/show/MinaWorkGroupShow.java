package com.rz.iot.bpo.mina.model.show;

import com.rz.iot.bpo.mina.model.MinaWorkGroup;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class MinaWorkGroupShow extends MinaWorkGroup {
   // 班次id
    private Integer classGenerateId;
}