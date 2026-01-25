package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);
}
