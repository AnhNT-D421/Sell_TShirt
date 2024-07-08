package com.example.asm_ph33338.controller;

import com.example.asm_ph33338.entity.BillEntity;
import com.example.asm_ph33338.repository.BillRepository;
import com.example.asm_ph33338.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private BillRepository billRepository;

    @GetMapping("/success")
    public String getBillSuccess(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Page<BillEntity> listBill = billService.getListBillPaid(page);
        model.addAttribute("listBill", listBill);
        return "bill/Homepage";
    }

    @GetMapping("/search")
    public String searchBillsByKey(@RequestParam("key") String key,
                                   @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Page<BillEntity> listBill = billService.getListBillPageByKey(key, page);
        model.addAttribute("listBill", listBill);
        return "bill/Homepage";
    }

    @GetMapping("/export-excel")
    public ResponseEntity<byte[]> exportBillsToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("listBill");
            List<BillEntity> listBill = billRepository.getListBillPaidAll();
            String[] headers = {"ID", "ID Staff", "Customer name", "Phone number customer", "Amount"};
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }
            int rowNum = 1;
            for (BillEntity bill : listBill) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(bill.getId());
                row.createCell(1).setCellValue(bill.getStaff().getId());
                row.createCell(2).setCellValue(bill.getCustomer().getName());
                row.createCell(3).setCellValue(bill.getCustomer().getPhoneNumber());
                row.createCell(4).setCellValue(bill.getTotalPayment());
            }
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            byte[] bytes = out.toByteArray();
            HttpHeaders headersResponse = new HttpHeaders();
            headersResponse.add("Content-Disposition", "attachment; filename=bills.xlsx");
            return new ResponseEntity<>(bytes, headersResponse, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
