package controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserCreateDto(
        @NotBlank(message = "Name is necessary")
        String name,

        @NotNull(message = "Birthday is necessary")
        LocalDate birthdayDate,

        @NotBlank(message = "CPF is necessary")
        @CPF(message = "CPF invalid")
                String cpf,

        @NotBlank(message = "RG is necessary")
        @Pattern(
                regexp = "^[0-9Xx.-]{5,14}$",
                message = "RG invalid"
        )
        String rg,

        @NotBlank(message = "Your email is necessary")
        @Email(message = "Invalid email")
        String email
) {
}
