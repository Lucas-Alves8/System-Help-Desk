package controller.dto;

import jakarta.validation.constraints.NotNull;
import model.enums.Role;

import java.util.UUID;

public record UserChangeRoleDto(
        @NotNull(message = "Role is necessary")
        Role role
        ) {
}
