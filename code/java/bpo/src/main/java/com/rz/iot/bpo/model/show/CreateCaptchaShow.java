package com.rz.iot.bpo.model.show;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author by xuxiake, Date on 2020/6/8 14:46.
 * PS: Not easy to write code, please indicate.
 * Description：
 */
@Data
public class CreateCaptchaShow {

    @ApiModelProperty(value = "uuid", example = "5dsdafafafd55sdad6s6a")
    private String uuid;
    @ApiModelProperty(value = "验证码的base64")
    private String captcha;
}
