package com.example.asm_ph33338.service;

import com.example.asm_ph33338.entity.StaffEntity;
import org.springframework.data.domain.Page;

public interface StaffService {
    public Page<StaffEntity> getAllStaffByPage(int pageNumber);

    public Page<StaffEntity> getListStaffByKey(String key, int pageNumber);

    public void craeteStaff(StaffEntity staff);

    public void updateStaff(StaffEntity staff);

    public void deteleStaff(Integer id);

    public StaffEntity findStaffByID(Integer id);

    public StaffEntity getStaffByUserAndPassword(String username, String password);

}
