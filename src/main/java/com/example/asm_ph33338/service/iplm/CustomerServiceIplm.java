package com.example.asm_ph33338.service.iplm;

import com.example.asm_ph33338.entity.CustomerEntity;
import com.example.asm_ph33338.repository.CustomerRepository;
import com.example.asm_ph33338.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceIplm implements CustomerService {
    @Autowired
    CustomerRepository repository;
    private static final int pageSize=5;
    @Override
    public Page<CustomerEntity> getAllCustomerByPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        return repository.findAll(pageable);
    }

    @Override
    public Page<CustomerEntity> searchCustomerPageByKey(String key, int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        return repository.searchCustomerPageByKey(key,pageable);
    }
    @Override
    public void craeteCustomer(CustomerEntity customer) {
            repository.save(customer);
    }

    @Override
    public void updateCustomer(CustomerEntity customer) {
            repository.save(customer);
    }

    @Override
    public void deteleCustomer(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public CustomerEntity findCustomerByID(Integer id) {
      return repository.findById(id).get();
    }

    @Override
    public CustomerEntity findCustomerEntityByPhoneNumber(String phoneNumber) {
        return repository.findCustomerEntityByPhoneNumber(phoneNumber);
    }
}
