package com.lucas_alves.helpdesk.mapper;

import controller.dto.CreateUserDto;
import model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(CreateUserDto createUserDto){

        User user = new User();
        user.setName(createUserDto.name());
        user.setBirthdayDate(createUserDto.birthdayDate());
        user.setCpf(createUserDto.cpf());
        user.setEmail(createUserDto.email());

        return user;
    }
}
