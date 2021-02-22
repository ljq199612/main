package com.xxx.module.soa.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDO implements Serializable {

    private Long id;
    private String idCard;
    private String name;
    private String phone;
}
