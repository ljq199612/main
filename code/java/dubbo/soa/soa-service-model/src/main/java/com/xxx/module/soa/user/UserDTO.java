package com.xxx.module.soa.user;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO{
    private Long id;
    private String idCard;
    private String name;
    private String phone;
}
