package model;

import jakarta.persistence.*;
import lombok.Data;
import model.enums.Sector;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "age", nullable = false, length = 3)
    private Integer age;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "rg", nullable = false, length = 14)
    private String rg;

    @Column(name = "email", nullable = false, length = 35)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "sector", nullable = false, length = 20)
    private Sector sector;
}
