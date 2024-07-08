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
@Table(name = "size")
public class SizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma_size")
    @NotBlank(message = "Code size must not be blank")
    private String code;

    @Column(name = "ten_size")
    @NotBlank(message = "Name size must not be blank")
    private String name;

    @Column(name = "trang_thai")
    @NotNull(message = "You haven't chosen status")
    private int status;
}
