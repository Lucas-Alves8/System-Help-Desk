package controller.dto;

import model.enums.Role;

import java.util.UUID;

public record UserChangeRoleDto(
        UUID id,
        Role role
        ) {
}
