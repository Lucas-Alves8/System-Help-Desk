package controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserFindByEmailDto(
        @NotBlank(message = "Your email is necessary")
        @Email(message = "Invalid email")
        String email) {
}
