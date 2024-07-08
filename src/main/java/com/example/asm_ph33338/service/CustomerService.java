package com.example.asm_ph33338.service;

import com.example.asm_ph33338.entity.CustomerEntity;
import com.example.asm_ph33338.entity.CustomerEntity;
import org.springframework.data.domain.Page;

public interface CustomerService {
    public Page<CustomerEntity> getAllCustomerByPage(int pageNumber);

    public Page<CustomerEntity> searchCustomerPageByKey(String key, int pageNumber);

    public void craeteCustomer(CustomerEntity customer);

    public void updateCustomer(CustomerEntity customer);

    public void deteleCustomer(Integer id);

    public CustomerEntity findCustomerByID(Integer id);

    public CustomerEntity findCustomerEntityByPhoneNumber(String phoneNumber);

}
