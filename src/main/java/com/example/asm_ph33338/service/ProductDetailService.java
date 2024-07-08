package com.example.asm_ph33338.service;

import com.example.asm_ph33338.entity.ProductDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailService {
    public List<ProductDetailEntity> getAllProductDetail();
    public Page<ProductDetailEntity> getAllProductDetailByPage(int pageNumber);

    public Page<ProductDetailEntity> searchProductDetailByKey(String key, int pageNumber);

    public void craeteProductDetail(ProductDetailEntity productDetail);

    public void updateProductDetail(ProductDetailEntity productDetail);

    public void deteleProductDetail(Integer id);

    public ProductDetailEntity findProductDetailByID(Integer id);

}
