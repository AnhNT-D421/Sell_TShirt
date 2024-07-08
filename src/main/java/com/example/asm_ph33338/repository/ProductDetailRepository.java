package com.example.asm_ph33338.repository;

import com.example.asm_ph33338.entity.ProductDetailEntity;
import com.example.asm_ph33338.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Integer> {
    @Query("select p from ProductDetailEntity p where p.name like %:key% or p.code like %:key% or p.color.name like %:key% " +
            "or p.product.name like %:key% or p.size.name like %:key%")
    public Page<ProductDetailEntity> searchProductDetailByKey(@Param("key") String key, Pageable pageable);
}
