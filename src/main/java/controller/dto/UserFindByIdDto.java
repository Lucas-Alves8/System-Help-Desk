package controller.dto;

import model.enums.Role;
import model.enums.Sector;

import java.time.LocalDate;
import java.util.UUID;

public record UserFindById(
        UUID id,
        String name,
        LocalDate birthdayDate,
        String email,
        Sector sector,
        Role role
) {
}
