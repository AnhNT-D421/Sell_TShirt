package com.example.asm_ph33338.service.iplm;

import com.example.asm_ph33338.entity.SizeEntity;
import com.example.asm_ph33338.repository.SizeRepository;
import com.example.asm_ph33338.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceIplm implements SizeService {
    @Autowired
    private SizeRepository repository;
    private static final int pageSize = 5;

    @Override
    public Page<SizeEntity> getAllSizeByPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repository.findAll(pageable);
    }

    @Override
    public Page<SizeEntity> searchSizeByKey(String key, int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable =PageRequest.of(pageNumber,pageSize,sort);
        return repository.searchSizeByKey(key,pageable);
    }

    @Override
    public List<SizeEntity> getAllSizeNonPaging() {
        return repository.findAll();
    }

    @Override
    public void craeteSize(SizeEntity size) {
        repository.save(size);
    }

    @Override
    public void updateSize(SizeEntity size) {
        repository.save(size);
    }

    @Override
    public void deleteSize(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public SizeEntity findSizeByID(Integer id) {
        return repository.findById(id).get();
    }
}
