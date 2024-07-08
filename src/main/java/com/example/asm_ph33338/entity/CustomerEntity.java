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
@Table(name = "khach_hang")
public class CustomerEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotBlank(message = "Name customer must not be blank ")
    @Column(name = "ho_ten")
    private String name;
    @NotBlank(message = "Phone number must not be blank")
    @Column(name = "sdt")
    private String phoneNumber;
    @NotBlank(message = "Address must not be blank")
    @Column(name = "dia_chi")
    private String address;
    @NotNull(message = "Your hasn't choose status customer")
    @Column(name = "trang_thai")
    private int status;

}
