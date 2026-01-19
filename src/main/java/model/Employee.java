package model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

public class Employee {

    @Column(name = "id", nullable = false)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "sector", nullable = false, length = 20)
    private Sector sector;
}
