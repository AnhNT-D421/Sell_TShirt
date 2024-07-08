package com.example.asm_ph33338.service;

import com.example.asm_ph33338.entity.BillDetailEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillDetailService {
    public Page<BillDetailEntity> getAllBillDetailByPage(int pageNumber);
    public List<BillDetailEntity> getListBillDetailByIDBill(int idBill);
    public void craeteBillDetail(BillDetailEntity bill);
    public void updateBillDetail(BillDetailEntity bill);
    public void deteleBillDetail(Integer id);
    public BillDetailEntity findBillDetailByID(Integer id);

}
