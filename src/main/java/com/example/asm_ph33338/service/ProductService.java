package com.example.asm_ph33338.service;

import com.example.asm_ph33338.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {
    public Page<ProductEntity> getAllProductByPage(int pageNumber);
    public Page<ProductEntity> searchProductPageByName(String key,int pageNumber);
    public List<ProductEntity> getAllProductNonPaging();
    public void craeteProduct(ProductEntity product);
    public void updateProduct(ProductEntity product);
    public void deteleProduct(Integer id);
    public ProductEntity findProductByID(Integer id);

}
