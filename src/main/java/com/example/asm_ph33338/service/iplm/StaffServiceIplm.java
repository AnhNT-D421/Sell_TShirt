package com.example.asm_ph33338.service.iplm;

import com.example.asm_ph33338.entity.StaffEntity;
import com.example.asm_ph33338.repository.StaffRepository;
import com.example.asm_ph33338.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceIplm implements StaffService {
    @Autowired
    private StaffRepository repository;
    private static final int pageSize = 5;

    @Override
    public Page<StaffEntity> getAllStaffByPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repository.findAll(pageable);
    }

    @Override
    public Page<StaffEntity> getListStaffByKey(String key, int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repository.getListStaffByKey(key, pageable);
    }

    @Override
    public void craeteStaff(StaffEntity staff) {
        repository.save(staff);
    }

    @Override
    public void updateStaff(StaffEntity staff) {
        repository.save(staff);
    }

    @Override
    public void deteleStaff(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public StaffEntity findStaffByID(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public StaffEntity getStaffByUserAndPassword(String username, String password) {
        return repository.getStaffByUserAndPassword(username, password);
    }
}
