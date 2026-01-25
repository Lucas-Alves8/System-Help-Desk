package service;

import controller.dto.*;
import mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPORTTI')")
    public User createUser(UserCreateDto userCreateDto) {

        if (userRepository.existsByCpf(userCreateDto.cpf())) {
            throw new RuntimeException("Cpf already registred");
        }

        if (userRepository.existsByEmail(userCreateDto.email())) {
            throw new RuntimeException("Email already used");
        }

        User user = mapper.toEntity(userCreateDto);

        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(UserUpdateDto userUpdateDto) {

        User user = userRepository.findById(userUpdateDto.id()).orElseThrow(() -> new RuntimeException("User not found"));

        mapper.toEntity(userUpdateDto, user);

        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(UUID id) {

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(user);
    }

    @PreAuthorize("hasAnyRole( 'ADMIN', 'SUPPORTTI')")
    public List<UserFindAllDto> listUser() {

        return userRepository.findAll()
                .stream()
                .map(mapper::toFindAll)
                .toList();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPORTTI')")
    public UserFindByIdDto findById(UUID id) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found or id wrong"));

        return mapper.toFindById(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPORTTI')")
    public UserFindByEmailDto findByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toFindByEmailDto(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPORTTI')")
    public UserFindByCpfDto findByCpf(String cpf) {

        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toFindByCpfDto(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void changeUserRoleDto(UserChangeRoleDto userChangeRoleDto) {

        User user = userRepository.findById(userChangeRoleDto.id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(userChangeRoleDto.role());

        userRepository.save(user);
    }
}