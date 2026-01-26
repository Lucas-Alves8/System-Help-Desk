package controller;

import controller.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User userCreate(@RequestBody @Valid UserCreateDto userCreateDto) {
        return userService.userCreate(userCreateDto);
    }

    @PutMapping("/{id}")
    public User userUpdate(@PathVariable UUID id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return userService.userUpdate(userUpdateDto);
    }

    @PutMapping("/{id}/role")
    public void userChangeRoleDto(@PathVariable UUID id, @RequestBody @Valid UserChangeRoleDto userChangeRoleDto ) {
        userService.userChangeRoleDto(id, userChangeRoleDto);
    }

    @GetMapping
    public List<UserFindAllDto> listAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/{id}")
    public UserFindByIdDto findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping("/email")
    public UserFindByEmailDto findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/cpf")
    public UserFindByCpfDto findByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf);
    }

    @DeleteMapping void UserDelete(@PathVariable UUID id) {
        userService.userDelete(id);
    }
}
