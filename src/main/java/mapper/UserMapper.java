package mapper;

import controller.dto.*;
import model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateDto userCreateDto) {

        User user = new User();
        user.setName(userCreateDto.name());
        user.setBirthdayDate(userCreateDto.birthdayDate());
        user.setCpf(userCreateDto.cpf());
        user.setEmail(userCreateDto.email());

        return user;
    }

    public void toEntity(UserUpdateDto userUpdateDto, User user) {

        user.setName(userUpdateDto.name());
        user.setBirthdayDate(userUpdateDto.birthdayDate());
        user.setCpf(userUpdateDto.cpf());
        user.setRg(userUpdateDto.rg());
        user.setEmail(userUpdateDto.email());
        user.setSector(userUpdateDto.sector());
        user.setRole(userUpdateDto.role());
    }

    public UserFindAllDto toFindAll(User user) {

        return new UserFindAllDto(
                user.getId(),
                user.getName(),
                user.getBirthdayDate(),
                user.getEmail(),
                user.getSector(),
                user.getRole()
        );
    }

    public UserFindByIdDto toFindById(User user) {

        return new UserFindByIdDto(
                user.getId(),
                user.getName(),
                user.getBirthdayDate(),
                user.getEmail(),
                user.getSector(),
                user.getRole()
        );
    }

    public UserFindByEmailDto toFindByEmailDto (User user) {
        return new UserFindByEmailDto(
                user.getEmail()
        );
}

    public UserFindByCpfDto toFindByCpfDto (User user) {
        return new UserFindByCpfDto(
            user.getCpf()
        );
    }
    public UserChangeRoleDto toChangeUserRole(User user) {

        return new UserChangeRoleDto(
                user.getId(),
                user.getRole()
        );
    }
}
