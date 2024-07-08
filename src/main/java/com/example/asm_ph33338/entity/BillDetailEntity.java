package com.example.asm_ph33338.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hdct")
public class BillDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String  id;
    @ManyToOne
    @JoinColumn(name = "id_hoa_don", referencedColumnName = "id")
    private BillEntity bill;
    @ManyToOne
    @JoinColumn(name = "id_ctsp",referencedColumnName = "id")
    private ProductDetailEntity productDetail;
    @Column(name = "so_luong_mua")
    private int quantity;
    @Column(name = "gia_ban")
    private double price;
    @Column(name = "trang_thai")
    private boolean status;
}
