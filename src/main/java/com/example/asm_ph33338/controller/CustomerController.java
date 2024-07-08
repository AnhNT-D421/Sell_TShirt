package com.example.asm_ph33338.controller;

import com.example.asm_ph33338.entity.CustomerEntity;
import com.example.asm_ph33338.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/home")
    public String getFormHomeCustomer(@RequestParam(name = "page", defaultValue = "0") int page,
                                      Model model) {
        Page<CustomerEntity> listCustomer = service.getAllCustomerByPage(page);
        model.addAttribute("listCustomer", listCustomer);
        return "customer/HomepageCustomer";
    }


    @GetMapping("/create")
    public String getFormAdCustomer(Model model) {
        model.addAttribute("customer", new CustomerEntity());
        return "customer/CreateCustomer";
    }
    @PostMapping("/create")
    public String addNewCustomer(@Valid @ModelAttribute("customer") CustomerEntity customer, BindingResult result,RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "customer/CreateCustomer";
        }
        service.craeteCustomer(customer);
        ra.addFlashAttribute("messages","Craete successfully new customer");
        return "redirect:/customer/home";
    }
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deteleCustomer(id);
        ra.addFlashAttribute("messages","Delete successfully customer with ID:"+id);
        return "redirect:/customer/home";
    }
    @GetMapping("/detail/{id}")
    public  String getFormUpdateCustomer(@PathVariable("id") Integer id,Model model){
        CustomerEntity customerDetail = service.findCustomerByID(id);
        model.addAttribute("customerDetail",customerDetail);
        model.addAttribute("message","UPDATE CUSTOMER WITH ID: "+id);
        return "customer/UpdateCustomer";
    }
    @PostMapping("/update")
    public String updateCustomer(@Valid @ModelAttribute("customerDetail") CustomerEntity customer,BindingResult result,RedirectAttributes ra){
        if(result.hasErrors()){
            return "customer/UpdateCustomer";
        }
        service.updateCustomer(customer);
        ra.addFlashAttribute("messages","Update successfully this customer with ID: "+customer.getId());
        return "redirect:/customer/home";
    }

    @GetMapping("/search")
    public String searchCustomerByKey(@RequestParam("key")String key,
                                      @RequestParam(name = "page",defaultValue = "0") int page,
                                      Model model){
        Page<CustomerEntity> listCustomer = service.searchCustomerPageByKey(key,page);
        model.addAttribute("listCustomer", listCustomer);
        return "customer/HomepageCustomer";
    }



}
