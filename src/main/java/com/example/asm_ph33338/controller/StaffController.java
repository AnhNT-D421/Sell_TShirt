package com.example.asm_ph33338.controller;

import com.example.asm_ph33338.entity.StaffEntity;
import com.example.asm_ph33338.service.StaffService;
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
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService service;

    @GetMapping("/home")
    public String getFormHomeStaff(@RequestParam(name = "page", defaultValue = "0") int page,
                                   Model model) {
        Page<StaffEntity> listByPage = service.getAllStaffByPage(page);
        model.addAttribute("listStaff", listByPage);
        return "staff/HomepageStaff";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deteleStaff(id);
        ra.addFlashAttribute("messages", "Delete successfully staff with ID: " + id);
        return "redirect:/staff/home";
    }

    @GetMapping("/create")
    public String getFormCreate(Model model) {
        model.addAttribute("staff", new StaffEntity());
        return "staff/CreateStaff";
    }

    @PostMapping("/create")
    public String createNewStaff(@Valid @ModelAttribute("staff") StaffEntity staff, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "staff/CreateStaff";
        }
        service.craeteStaff(staff);
        ra.addFlashAttribute("messages", "Craete successfully new staff");
        return "redirect:/staff/home";
    }

    @GetMapping("/detail/{id}")
    public String getFormUpdate(@PathVariable("id") Integer id, Model model) {
        StaffEntity staffDetail = service.findStaffByID(id);
        model.addAttribute("staffDetail", staffDetail);
        model.addAttribute("messages", "Update Staff With ID: " + id);
        return "staff/UpdateStaff";
    }

    @PostMapping("/update")
    public String updateStaff(@Valid @ModelAttribute("staffDetail") StaffEntity staff, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "staff/UpdateStaff";
        }
        service.updateStaff(staff);
        ra.addFlashAttribute("messages", "Update successfully this staff");
        return "redirect:/staff/home";
    }

    @GetMapping("/search")
    public String searchStaffByKey(@RequestParam("key") String key,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   Model model
                                   ) {
        Page<StaffEntity> listKeyByPage = service.getListStaffByKey(key,page);
        model.addAttribute("listStaff",listKeyByPage);
        return "staff/HomepageStaff";
    }
}
