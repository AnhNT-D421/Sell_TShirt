package com.example.asm_ph33338.service;

import com.example.asm_ph33338.entity.ColorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColorService {
    public Page<ColorEntity> getAllColorByPage(int pageNumber);
    public Page<ColorEntity> searchColorsByKey(String key, int pageNumber);
    public List<ColorEntity> getAllColorNonPaging();
    public void craeteColor(ColorEntity color);
    public void updateColor(ColorEntity color);
    public void deteleColor(Integer id);
    public ColorEntity findColorByID(Integer id);

}
