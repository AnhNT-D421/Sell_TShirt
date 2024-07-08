package com.example.asm_ph33338.repository;

import com.example.asm_ph33338.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    @Query("select p from  ProductEntity p where p.name like %:key% or p.code like %:key")
    public Page<ProductEntity> searchProductPageByName(@Param("key")String key, Pageable pageable);
}
