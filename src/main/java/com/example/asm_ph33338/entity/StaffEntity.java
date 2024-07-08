package com.example.asm_ph33338.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255)
    @NotBlank(message = "Name staff must not be blank")
    private String name;

    @Column(name = "code", length = 255)
    @NotBlank(message = "Code staff must not be blank")
    private String code;

    @Column(name = "username", length = 255)
    @NotBlank(message = "Username staff must not be blank")
    private String username;

    @Column(name = "password", length = 255)
    @NotBlank(message = "Password staff must not be blank")
    private String password;

    @Column(name = "trang_thai")
    @NotNull(message = "You haven't chosen status")
    private int status;
}
