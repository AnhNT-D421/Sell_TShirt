package com.example.asm_ph33338.repository;

import com.example.asm_ph33338.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    @Query("select c from CustomerEntity c where c.name like%:key% or c.address like %:key% or c.phoneNumber like %:key%")
    public Page<CustomerEntity> searchCustomerPageByKey(@Param("key")String key, Pageable pageable);
    public CustomerEntity findCustomerEntityByPhoneNumber(String phoneNumber);
}
