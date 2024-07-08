package com.example.asm_ph33338.service.iplm;

import com.example.asm_ph33338.entity.ColorEntity;
import com.example.asm_ph33338.repository.ColorRepository;
import com.example.asm_ph33338.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceIplm implements ColorService {
    @Autowired
    ColorRepository  repository;
    private static final int pageSize=5;
    @Override
    public Page<ColorEntity> getAllColorByPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable  = PageRequest.of(pageNumber,pageSize,sort);
        return repository.findAll(pageable);
    }

    @Override
    public Page<ColorEntity> searchColorsByKey(String key, int pageNumber) {
        Sort sort= Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable =PageRequest.of(pageNumber,pageSize,sort);
        return repository.searchColorsByKey(key,pageable);
    }

    @Override
    public List<ColorEntity> getAllColorNonPaging() {
        return repository.findAll();
    }

    @Override
    public void craeteColor(ColorEntity color) {
        repository.save(color);
    }

    @Override
    public void updateColor(ColorEntity color) {
        repository.save(color);
    }

    @Override
    public void deteleColor(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ColorEntity findColorByID(Integer id) {
       return repository.findById(id).get();
    }
}
