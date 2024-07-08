package com.example.asm_ph33338.service.iplm;

import com.example.asm_ph33338.entity.ProductEntity;
import com.example.asm_ph33338.repository.ProductRepository;
import com.example.asm_ph33338.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProuductServiceIplm implements ProductService {
    private static final int pageSize = 5;
    @Autowired
    ProductRepository repository;

    @Override
    public Page<ProductEntity> getAllProductByPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repository.findAll(pageable);
    }

    @Override
    public Page<ProductEntity> searchProductPageByName(String key, int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repository.searchProductPageByName(key,pageable);
    }

    @Override
    public List<ProductEntity> getAllProductNonPaging() {
        return repository.findAll();
    }

    @Override
    public void craeteProduct(ProductEntity product) {
        repository.save(product);
    }

    @Override
    public void updateProduct(ProductEntity product) {
        repository.save(product);
    }

    @Override
    public void deteleProduct(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ProductEntity findProductByID(Integer id) {
        return repository.findById(id).get();
    }
}
