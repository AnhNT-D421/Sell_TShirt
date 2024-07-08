package com.example.asm_ph33338.repository;

import com.example.asm_ph33338.entity.ProductEntity;
import com.example.asm_ph33338.entity.SizeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {
    @Query("select s from SizeEntity s where s.name like %:key% or s.code like %:key%")
    public Page<SizeEntity> searchSizeByKey(@Param("key") String key, Pageable pageable);
}
