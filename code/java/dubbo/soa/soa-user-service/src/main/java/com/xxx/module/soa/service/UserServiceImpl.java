package com.xxx.module.soa.service;


import com.xxx.module.soa.user.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO getUse(Long id) {
        return UserDTO.builder()
                .id(id)
                .idCard("3402231995")
                .name("ljq")
                .phone("136XXX9197")
                .build();
    }
}
