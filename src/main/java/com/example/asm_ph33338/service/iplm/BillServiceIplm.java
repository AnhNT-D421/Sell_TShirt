package com.example.asm_ph33338.service.iplm;

import com.example.asm_ph33338.entity.BillEntity;
import com.example.asm_ph33338.repository.BillRepository;
import com.example.asm_ph33338.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceIplm implements BillService {
    @Autowired
    private BillRepository repository;

    private static final int pageSize = 5;

    @Override
    public Page<BillEntity> getAllBillByPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repository.findAll(pageable);
    }

    @Override
    public List<BillEntity> getAllBillUnPaid() {
        return repository.getListBillUnpaid();
    }

    @Override
    public Page<BillEntity> getListBillPaid(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repository.getListBillPaid(pageNumber,pageable);
    }

    @Override
    public Page<BillEntity> getListBillPageByKey(String key, int pageNumber) {
        Sort sort= Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable  = PageRequest.of(pageNumber,pageSize,sort);
        return repository.getListBillPageByKey(key,pageable);
    }

    @Override
    public List<BillEntity> getListBillPaidAll() {
        return repository.getListBillPaidAll();
    }

    @Override
    public void craeteBill(BillEntity bill) {
        repository.save(bill);
    }

    @Override
    public void updateBill(BillEntity bill) {
        repository.save(bill);
    }

    @Override
    public void deteleBill(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public BillEntity findBillByID(Integer id) {
        return repository.findById(id).get();
    }
}
