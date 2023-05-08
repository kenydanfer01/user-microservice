package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false, length = 10)
    private String dni;

    @Column(unique = true, nullable = false, length = 13)
    private String phone;

    private LocalDate birthday;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    private String tokenPassword;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity roleEntity;
}
