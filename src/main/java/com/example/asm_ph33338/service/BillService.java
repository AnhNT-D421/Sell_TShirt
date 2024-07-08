package com.example.asm_ph33338.service;

import com.example.asm_ph33338.entity.BillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillService {
    public Page<BillEntity> getAllBillByPage(int pageNumber);

    public List<BillEntity> getAllBillUnPaid();

    public Page<BillEntity> getListBillPaid(int pageNumber);

    public Page<BillEntity> getListBillPageByKey(String key, int pageNumber);
    public List<BillEntity> getListBillPaidAll();

    public void craeteBill(BillEntity bill);

    public void updateBill(BillEntity bill);

    public void deteleBill(Integer id);

    public BillEntity findBillByID(Integer id);

}
