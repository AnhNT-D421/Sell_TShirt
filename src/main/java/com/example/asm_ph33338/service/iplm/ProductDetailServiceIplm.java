package com.example.asm_ph33338.service.iplm;

import com.example.asm_ph33338.entity.ProductDetailEntity;
import com.example.asm_ph33338.repository.ProductDetailRepository;
import com.example.asm_ph33338.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceIplm implements ProductDetailService {
    @Autowired
    private ProductDetailRepository repository;
    private static final int pageSize = 4;

    @Override
    public List<ProductDetailEntity> getAllProductDetail() {
        return repository.findAll();
    }

    @Override
    public Page<ProductDetailEntity> getAllProductDetailByPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        return repository.findAll(pageable);
    }

    @Override
    public Page<ProductDetailEntity> searchProductDetailByKey(String key, int pageNumber) {
        Sort sort= Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable =PageRequest.of(pageNumber,pageSize,sort);
        return repository.searchProductDetailByKey(key,pageable);
    }

    @Override
    public void craeteProductDetail(ProductDetailEntity productDetail) {
        repository.save(productDetail);
    }

    @Override
    public void updateProductDetail(ProductDetailEntity productDetail) {
        repository.save(productDetail);
    }

    @Override
    public void deteleProductDetail(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ProductDetailEntity findProductDetailByID(Integer id) {
        return repository.findById(id).get();
    }
}
