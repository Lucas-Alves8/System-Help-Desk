package service;

import com.lucas_alves.helpdesk.mapper.UserMapper;
import controller.dto.CreateUserDto;
import controller.dto.UpdateUserDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Data
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPORTTI')")
    public User createUser(CreateUserDto createUserDto) {

        if (userRepository.existsByCpf(createUserDto.cpf())) {
            throw new RuntimeException("Cpf already registred");
        }

        if (userRepository.existsByEmail(createUserDto.email())) {
            throw new RuntimeException("Email already used");
        }

        User user = mapper.toEntity(createUserDto);

        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(UpdateUserDto updateUserDto) {

        User user = userRepository.findById(updateUserDto.id()).orElseThrow(() -> new RuntimeException("User not found"));

        mapper.toEntity(updateUserDto, user);

        return userRepository.save(user);
    }
}
