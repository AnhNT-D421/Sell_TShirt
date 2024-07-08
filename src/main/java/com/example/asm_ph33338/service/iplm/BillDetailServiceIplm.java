package com.example.asm_ph33338.service.iplm;

import com.example.asm_ph33338.entity.BillDetailEntity;
import com.example.asm_ph33338.repository.BillDetailRepository;
import com.example.asm_ph33338.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceIplm implements BillDetailService {
    @Autowired
    private BillDetailRepository repository;
    private static  final int pageSize = 5;
    @Override
    public Page<BillDetailEntity> getAllBillDetailByPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        return repository.findAll(pageable);
    }

    @Override
    public List<BillDetailEntity> getListBillDetailByIDBill(int idBill) {
        return repository.getListBillDetailByIDBill(idBill);
    }

    @Override
    public void craeteBillDetail(BillDetailEntity bill) {
        repository.save(bill);
    }

    @Override
    public void updateBillDetail(BillDetailEntity bill) {
        repository.save(bill);
    }

    @Override
    public void deteleBillDetail(Integer id) {
      repository.deleteById(id);

    }

    @Override
    public BillDetailEntity findBillDetailByID(Integer id) {
       return  repository.findById(id).get();
    }
}
