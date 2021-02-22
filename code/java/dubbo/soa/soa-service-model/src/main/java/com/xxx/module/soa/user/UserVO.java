package com.xxx.module.soa.user;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor

@AllArgsConstructor
@Builder
public class UserVO {

    private Long id;
    private String idCard;
    private String name;
    private String phone;

}
