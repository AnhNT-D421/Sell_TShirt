package com.example.asm_ph33338.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ctsp")
public class ProductDetailEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_sp")
    @NotNull(message = "Product must not be null")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    @NotNull(message = "Color must not be null")
    private ColorEntity color;
    @ManyToOne
    @JoinColumn(name = "id_size")
    @NotNull(message = "Size must not be null")
    private SizeEntity size;
    @NotBlank(message = "Code must not be blank")
    @Size(max = 50, message = "Code cannot be longer than 50 characters")
    @Column(name = "ma_ctsp")
    private String code;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    @Column(name = "ten_ctsp")
    private String name;
    @Column(name = "images")
    private String imageFileName;
    @NotNull(message = "Quantity must not be null")
    @Min(value = 1, message = "Quantity must be equal to or greater than 0")
    @Column(name = "so_luong_ton")
    private int quantity;
    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "1",message = "Price must be greater than 0")
    @Column(name = "gia_ban")
    private double price;
    @Column(name = "trang_thai")
    private int status;
}
