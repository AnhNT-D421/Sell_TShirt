package com.example.asm_ph33338.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "hoa_don")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_nhan_vien")
    private StaffEntity staff;
    @OneToOne
    @JoinColumn(name = "id_khach_hang")
    private CustomerEntity customer;
    @Column(name = "tong_tien")
    private double  totalPayment;
    @Column(name = "trang_thai")
    private int status;
}
