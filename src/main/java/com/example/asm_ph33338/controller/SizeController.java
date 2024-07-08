package com.example.asm_ph33338.controller;

import com.example.asm_ph33338.entity.SizeEntity;
import com.example.asm_ph33338.service.SizeService;
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
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeService service;

    @GetMapping("/home")
    public String getFormHome(@RequestParam(name = "page", defaultValue = "0") int page,
                              Model model) {
        Page<SizeEntity> listByPage = service.getAllSizeByPage(page);
        model.addAttribute("listSize", listByPage);
        return "size/HomepageSize";
    }

    @GetMapping("/delete/{id}")
    public String deleteSize(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deleteSize(id);
        ra.addFlashAttribute("messages", "Delete successfully size with ID: " + id);
        return "redirect:/size/home";
    }

    @GetMapping("/create")
    public String getFormCreate(Model model) {
        model.addAttribute("size", new SizeEntity());
        return "size/CreateSize";
    }

    @PostMapping("/create")
    public String createNewSize(@Valid @ModelAttribute("size") SizeEntity size, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "size/CreateSize";
        }
        service.craeteSize(size);
        ra.addFlashAttribute("messages", "Create successfully new size");
        return "redirect:/size/home";
    }

    @GetMapping("/detail/{id}")
    public String getFormUpdate(@PathVariable("id") Integer id, Model model) {
        SizeEntity sizeDetail = service.findSizeByID(id);
        model.addAttribute("sizeDetail", sizeDetail);
        model.addAttribute("title", "Update size with ID: " + id);
        return "size/UpdateSize";
    }

    @PostMapping("/update")
    public String updateSize(@Valid @ModelAttribute("sizeDetail") SizeEntity size, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "size/UpdateSize";
        }
        service.updateSize(size);
        ra.addFlashAttribute("messages", "Update successfully this size");
        return "redirect:/size/home";
    }

    @GetMapping("/search")
    public String searchSize(@RequestParam("key") String key,
                             @RequestParam(name = "page",defaultValue = "0") int page,
                             Model model) {
        Page<SizeEntity> listSearch = service.searchSizeByKey(key,page);
        model.addAttribute("listSize",listSearch);
        return "size/HomepageSize";
    }
}
