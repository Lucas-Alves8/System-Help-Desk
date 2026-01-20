package model;

import jakarta.persistence.*;
import lombok.Data;
import model.enums.Role;
import model.enums.Sector;
import model.enums.SupportTiLevel;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "birthday_date", nullable = false)
    private LocalDate birthdayDate;

    @Column(name = "rg", nullable = false, length = 15)
    private String rg;

    @Column(name = "cpf", nullable = false, length = 11, unique = true, updatable = false)
    private String cpf;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "support_ti_level", length = 15)
    private SupportTiLevel supportTiLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "sector", length = 20)
    private Sector sector;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 15, nullable = false)
    private Role role;

    @Transient
    public int getAge () {
        return Period.between(birthdayDate, LocalDate.now()).getYears();
    }
}
