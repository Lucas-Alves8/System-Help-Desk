package controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import model.enums.Role;
import model.enums.Sector;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

public record UserUpdateDto(
        @NotNull(message = "Id is necessary")
        UUID id,

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
        String email,


        @NotNull(message = "Sector is necessary")
        Sector sector,

        @NotNull(message = "Role is necessary")
        Role role) {

}
