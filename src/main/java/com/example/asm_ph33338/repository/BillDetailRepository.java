package com.example.asm_ph33338.repository;

import com.example.asm_ph33338.entity.BillDetailEntity;
import com.example.asm_ph33338.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetailEntity,Integer> {
    @Query("select b from BillDetailEntity b where b.bill.id=:idBill")
    public List<BillDetailEntity> getListBillDetailByIDBill(@Param("idBill")Integer id);
}
