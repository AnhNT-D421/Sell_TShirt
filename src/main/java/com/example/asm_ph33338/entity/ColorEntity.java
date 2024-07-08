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
@Table(name = "mau_sac")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotBlank(message = "Code color must be not blank")
    @Column(name = "ma_mau")
    private String code;
    @NotBlank(message = "Name color must be not blank")
    @Column(name = "ten_mau")
    private String name;
    @NotNull(message = "You hasn't choose status")
    @Column(name = "trang_thai")
    private int status;
}
