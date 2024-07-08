package com.example.asm_ph33338.repository;

import com.example.asm_ph33338.entity.ProductEntity;
import com.example.asm_ph33338.entity.StaffEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity,Integer> {
    @Query("select s from StaffEntity s where s.username =:username and s.password=:password")
    StaffEntity getStaffByUserAndPassword(@Param("username")String username,@Param("password") String password);
    @Query("select s from StaffEntity s where s.code like %:key% or s.name like %:key% or s.username like %:key%")
    Page<StaffEntity> getListStaffByKey(@Param("key")String key, Pageable pageable);
}
