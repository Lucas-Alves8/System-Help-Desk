package com.lucas_alves.helpdesk.mapper;

import controller.dto.CreateUserDto;
import controller.dto.UpdateUserDto;
import controller.dto.UserFindAll;
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

    public void toEntity(UpdateUserDto updateUserDto, User user) {

        user.setName(updateUserDto.name());
        user.setBirthdayDate(updateUserDto.birthdayDate());
        user.setCpf(updateUserDto.cpf());
        user.setRg(updateUserDto.rg());
        user.setEmail(updateUserDto.email());
        user.setSector(updateUserDto.sector());
        user.setRole(updateUserDto.role());
    }
    public void toEntity(User user) {

        return new UserFindAll(
                user.getId(),
                user.getName(),
                user.getBirthdayDate(),
                user.getEmail(),
                user.getSector(),
                user.getRole()
        );
    }
}
