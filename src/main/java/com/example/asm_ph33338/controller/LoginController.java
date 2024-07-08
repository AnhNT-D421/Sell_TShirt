package com.example.asm_ph33338.controller;

import com.example.asm_ph33338.entity.*;
import com.example.asm_ph33338.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private BillService billService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("/form")
    public String getFormLogin(@RequestParam(required = false) String username,
                               @RequestParam(required = false) String password,
                               Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "user/Login";
    }

    @PostMapping("/home")
    public String loginSuccess(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               Model model, HttpSession session) {
        StaffEntity staff = staffService.getStaffByUserAndPassword(username, password);
        if (staff != null) {
            session.setAttribute("IDStaff", staff.getId());
            session.setAttribute(("roleCheck"), staff.getUsername());
            Page<ProductDetailEntity> listProductDetail = productDetailService.getAllProductDetailByPage(page);
            model.addAttribute("listProductDetail", listProductDetail);
            model.addAttribute("staff", staff);
            model.addAttribute("listBill", billService.getAllBillUnPaid());
            return "home/Homepage";
        } else {
            model.addAttribute("error", "Your account or password is incorrect!");
            return "user/Login";
        }
    }

    @GetMapping("/home")
    public String getDataAtHomePage(@RequestParam(name = "page", defaultValue = "0") int page,
                                    Model model, HttpSession session) {
        Integer idBill = (Integer) session.getAttribute("selectedBillId");
        String errorListSize = null;
        model.addAttribute("listBill", billService.getAllBillUnPaid());
        model.addAttribute("listProductDetail", productDetailService.getAllProductDetailByPage(page));
        if (billService.getAllBillUnPaid().size() >= 5) {
            errorListSize = "Cannot create more than 5 bills";
            model.addAttribute("errorListSize", errorListSize);
        }
        return "home/Homepage";
    }

    @PostMapping("/create-new-bill")
    public String createNewBill(HttpSession session) {
        Integer idStaff = (Integer) session.getAttribute("IDStaff");
        if (idStaff != null) {
            BillEntity bill = new BillEntity();
            StaffEntity staff = new StaffEntity();
            staff.setId(idStaff);
            bill.setStaff(staff);
            bill.setStatus(0);
            billService.craeteBill(bill);
            return "redirect:/login/home";
        } else {
            return "redirect:/login/form";
        }
    }

    @GetMapping("/delete-bill/{id}")
    public String deleteBill(@PathVariable("id") Integer id) {
        billService.deteleBill(id);
        return "redirect:/login/home";
    }

    @GetMapping("/delete-bill-success/{id}")
    public String deleteBillSuccess(@PathVariable("id") Integer id) {
        billService.deteleBill(id);
        return "redirect:/bill/success";
    }
    @GetMapping("/choose-bill/{id}")
    public String chooseBill(@PathVariable("id") Integer id, Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             HttpSession session) {
        BillEntity billDetail = billService.findBillByID(id);
        List<BillDetailEntity> listBillDetail = billDetailService.getListBillDetailByIDBill(id);
        model.addAttribute("listBillDetail", listBillDetail);
        model.addAttribute("billDetail", billDetail);
        model.addAttribute("listBill", billService.getAllBillUnPaid());
        model.addAttribute("listProductDetail", productDetailService.getAllProductDetailByPage(page));
        session.setAttribute("selectedBillId", id);
        double totalAmount = 0.0;
        for (BillDetailEntity billDetailSetTotal : listBillDetail) {
            totalAmount += billDetailSetTotal.getPrice() * billDetailSetTotal.getQuantity();
        }
        model.addAttribute("totalAmount", totalAmount);
        return "home/Homepage";
    }

    @PostMapping("/search-customer")
    public String searchCustomerByPhoneNumber(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam("idBill") Integer idBill,
            Model model, HttpSession session) {
        List<BillDetailEntity> listBillDetail = billDetailService.getListBillDetailByIDBill(idBill);
        double totalAmount = 0.0;
        for (BillDetailEntity billDGetPrice : listBillDetail) {
            totalAmount += billDGetPrice.getPrice() * billDGetPrice.getQuantity();
        }
        listBillDetail = billDetailService.getListBillDetailByIDBill(idBill);
        CustomerEntity customerDetail = customerService.findCustomerEntityByPhoneNumber(phoneNumber);
        model.addAttribute("listProductDetail", productDetailService.getAllProductDetailByPage(page));
        model.addAttribute("listBill", billService.getAllBillUnPaid());
        model.addAttribute("billDetail", billService.findBillByID(idBill));
        model.addAttribute("listBillDetail", listBillDetail);
        model.addAttribute("customerDetail", customerDetail);
        model.addAttribute("totalAmount", totalAmount);
        session.setAttribute("customerDetail", customerDetail);
        return "home/Homepage";
    }

    @PostMapping("/create-bill-detail")
    public String createBillDetail(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam("idProductDt") Integer idProductDetail,
            @RequestParam("quantityBuy") int quantityBuy,
            Model model, HttpSession session) {
        List<BillDetailEntity> listBillDetail = new ArrayList<>();
        Integer idBillSeleted = (Integer) session.getAttribute("selectedBillId");
        String error = null;
        int quantityPresent = 0;
        for (ProductDetailEntity prddt : productDetailService.getAllProductDetail()) {
            if (prddt.getId().equals(idProductDetail)) {
                quantityPresent = prddt.getQuantity();
                break;
            }
        }
        ProductDetailEntity productDetail = productDetailService.findProductDetailByID(idProductDetail);
        BillEntity bill = new BillEntity();
        bill.setId(idBillSeleted);
        if (quantityBuy > productDetail.getQuantity()) {
            error = "Cannot buy this product because quantity buy is larger than quantity available";
            model.addAttribute("error", error);
            model.addAttribute("listBillDetail", listBillDetail);
            model.addAttribute("billDetail", bill);
            model.addAttribute("quantityBuy", quantityBuy);
            model.addAttribute("listProductDetail", productDetailService.getAllProductDetailByPage(page));
            productDetail.setQuantity(productDetail.getQuantity());
            model.addAttribute("listBill", billService.getAllBillUnPaid());
            return "home/Homepage";
        }
        int quantityRemaining = Math.max(0, quantityPresent - quantityBuy);
        model.addAttribute("quantityPresent", quantityPresent);
        model.addAttribute("quantityRemaining", quantityRemaining);
        listBillDetail = billDetailService.getListBillDetailByIDBill(idBillSeleted);
        if (listBillDetail == null) {
            listBillDetail = new ArrayList<>();
        }
        boolean foundInBill = false;
        for (BillDetailEntity billDetail : listBillDetail) {
            if (billDetail.getProductDetail().getId().equals(productDetail.getId())) {
                foundInBill = true;
                billDetail.setQuantity(billDetail.getQuantity() + quantityBuy);
                billDetail.setPrice(billDetail.getPrice() + productDetail.getPrice() * quantityBuy);
                productDetail.setQuantity(productDetail.getQuantity() - quantityBuy);
                billDetailService.updateBillDetail(billDetail);
                break;
            }
        }
        if (!foundInBill) {
            BillDetailEntity newBillDetail = new BillDetailEntity();
            newBillDetail.setBill(bill);
            newBillDetail.setProductDetail(productDetail);
            newBillDetail.setQuantity(quantityBuy);
            newBillDetail.setPrice(productDetail.getPrice() * quantityBuy);
            productDetail.setQuantity(productDetail.getQuantity() - quantityBuy);
            billDetailService.craeteBillDetail(newBillDetail);
            listBillDetail.add(newBillDetail);
        }
        double totalAmount = 0.0;
        for (BillDetailEntity billDetail : listBillDetail) {
            totalAmount += billDetail.getPrice() * billDetail.getQuantity();
        }
        model.addAttribute("listBillDetail", listBillDetail);
        model.addAttribute("billDetail", bill);
        model.addAttribute("quantityBuy", quantityBuy);
        model.addAttribute("listBill", billService.getAllBillUnPaid());
        model.addAttribute("totalAmount", totalAmount);
        session.setAttribute("totalAmount", totalAmount);
        session.setAttribute("selectedBillId", idBillSeleted);
        model.addAttribute("listProductDetail", productDetailService.getAllProductDetailByPage(page));
        return "home/Homepage";
    }

    @PostMapping("/payment")
    public String payment(HttpSession session, RedirectAttributes ra) {
        Integer idBill = (Integer) session.getAttribute("selectedBillId");
        double totalAmount = (Double) session.getAttribute("totalAmount");
        CustomerEntity customer = (CustomerEntity) session.getAttribute("customerDetail");
        if (idBill != null && totalAmount != 0 && customer != null) {
            BillEntity bill = billService.findBillByID(idBill);
            bill.setTotalPayment(totalAmount);
            bill.setCustomer(customer);
            bill.setStatus(1);
            billService.updateBill(bill);
            session.removeAttribute("customerDetail");
            session.removeAttribute("totalAmount");
            return "redirect:/bill/success";
        } else {
            return "redirect:/login/home";
        }
    }

    @GetMapping("/search-product")
    public String searchProductDetailByKey(@RequestParam("key") String key,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           Model model) {
        Page<ProductDetailEntity> listSearchByKey = productDetailService.searchProductDetailByKey(key, page);
        model.addAttribute("listProductDetail", listSearchByKey);
        model.addAttribute("listBill", billService.getAllBillUnPaid());
        return "home/Homepage";
    }
    @GetMapping("/update-bill-detail/{id}")
    public String deleteBillDetail(@PathVariable("id") Integer id,
                                   @RequestParam("quantityReturn") int quantityReturn,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   Model model) {
        BillDetailEntity billDetail = billDetailService.findBillDetailByID(id);
        ProductDetailEntity productDetail = billDetail.getProductDetail();
        Integer idBill = billDetail.getBill().getId();
        int quantityPresent = productDetail.getQuantity();
        int quantityBought = billDetail.getQuantity();
        int quantityBillAfterReturn = quantityBought - quantityReturn;
        int quantityProductAfterReturn = quantityPresent + quantityReturn;
        billDetail.setQuantity(quantityBillAfterReturn);
        productDetail.setQuantity(quantityProductAfterReturn);
        billDetailService.updateBillDetail(billDetail);
        productDetailService.updateProductDetail(productDetail);
        model.addAttribute("listBill", billService.getAllBillUnPaid());
        model.addAttribute("listProductDetail", productDetailService.getAllProductDetailByPage(page));
        model.addAttribute("listBillDetail", billDetailService.getListBillDetailByIDBill(idBill));
        return "redirect:/login/home";
    }

    @GetMapping("/delete-billdetail/{id}")
    public String deleteBillDetail(@PathVariable("id") Integer id) {
        billDetailService.deteleBillDetail(id);
        return "redirect:/login/home";
    }
}