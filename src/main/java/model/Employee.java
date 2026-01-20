package model;

import jakarta.persistence.*;
import lombok.Data;
import model.enums.Sector;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "dateBirthday", nullable = false, length = 3)
    private LocalDate birthdayDate;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "rg", nullable = false, length = 15)
    private String rg;

    @Column(name = "email", nullable = false, length = 35)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "sector", nullable = false, length = 20)
    private Sector sector;

    @Transient
    public int getAge () {
        return Period.between(birthdayDate, LocalDate.now()).getYears();
    }
}
