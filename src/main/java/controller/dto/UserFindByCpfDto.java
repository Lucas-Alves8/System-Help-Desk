package controller.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UserFindByCpfDto(
        @NotBlank(message = "CPF is required")
        @CPF(message = "Invalid CPF")
        String cpf) {
}
