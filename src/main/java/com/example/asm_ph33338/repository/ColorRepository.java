package com.example.asm_ph33338.repository;

import com.example.asm_ph33338.entity.ColorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity,Integer> {
    @Query("select c from  ColorEntity c where c.code like %:key% or c.name like %:key%")
    public Page<ColorEntity> searchColorsByKey(@Param("key")String key, Pageable pageable);
}
