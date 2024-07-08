package com.example.asm_ph33338.repository;

import com.example.asm_ph33338.entity.BillEntity;
import com.example.asm_ph33338.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity,Integer> {
    @Query("select b from BillEntity b where b.status=0")
    public List<BillEntity> getListBillUnpaid();
    @Query("select b from BillEntity b where b.status=1")
    public Page<BillEntity> getListBillPaid(int pageNumber, Pageable pageable);
    @Query("select  b from BillEntity b where b.customer.name like %:key% or b.staff.name like %:key%")
    public Page<BillEntity> getListBillPageByKey(@Param("key")String key, Pageable pageable);

    @Query("select b from BillEntity b where b.status=1")
    public List<BillEntity> getListBillPaidAll();
}
