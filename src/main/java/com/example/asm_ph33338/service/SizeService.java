package com.example.asm_ph33338.service;

import com.example.asm_ph33338.entity.SizeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SizeService {
    public Page<SizeEntity> getAllSizeByPage(int pageNumber);
    public Page<SizeEntity> searchSizeByKey(String key,int pageNumber);

    public List<SizeEntity> getAllSizeNonPaging();
    public void craeteSize(SizeEntity size);
    public void updateSize(SizeEntity size);
    public void deleteSize(Integer id);
    public SizeEntity findSizeByID(Integer id);

}
