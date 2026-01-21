package service;

import com.lucas_alves.helpdesk.mapper.UserMapper;
import controller.dto.CreateUserDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.time.LocalDate;

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
}
