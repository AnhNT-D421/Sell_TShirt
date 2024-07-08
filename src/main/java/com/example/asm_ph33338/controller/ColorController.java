package com.example.asm_ph33338.controller;

import com.example.asm_ph33338.entity.ColorEntity;
import com.example.asm_ph33338.service.ColorService;
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
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorService service;
    @GetMapping("/home")
    public String getFormHome(@RequestParam(name = "page", defaultValue = "0") int page,
                              Model model) {
        Page<ColorEntity> listByPage = service.getAllColorByPage(page);
        model.addAttribute("listColor", listByPage);
        return "color/HomepageColor";
    }

    @GetMapping("/delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deteleColor(id);
        ra.addFlashAttribute("messages", "Delete successfully color with ID: " + id);
        return "redirect:/color/home";
    }

    @GetMapping("/create")
    public String getFormCreate(Model model) {
        model.addAttribute("color", new ColorEntity());
        return "color/CreateColor";
    }

    @PostMapping("/create")
    public String craeteNewColor(@Valid @ModelAttribute("color") ColorEntity color, BindingResult result,
                                 RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "color/CreateColor";
        }
        service.craeteColor(color);
        ra.addFlashAttribute("messages","Create successfully new color");
        return "redirect:/color/home";
    }
    @GetMapping("/detail/{id}")
    public String getFormUpdate(@PathVariable("id")Integer id,Model model){
        ColorEntity colorDetail= service.findColorByID(id);
        model.addAttribute("colorDetail",colorDetail);
        model.addAttribute("title","Update Color With ID: "+id);
        return "color/UpdateColor";
    }
    @PostMapping("/update")
    public String updateColor(@Valid @ModelAttribute("colorDetail")ColorEntity color,BindingResult result,RedirectAttributes ra){
        if(result.hasErrors()){
            return "color/UpdateColor";
        }
        service.updateColor(color);
        ra.addFlashAttribute("messages","Update successfully color with ID: "+color.getId());
        return "redirect:/color/home";
    }
    @GetMapping("/search")
    public String searchColorByKey(@RequestParam("key")String key,
                                   @RequestParam(name = "page",defaultValue = "0")int page,
                                    Model model){
        Page<ColorEntity> listSearchByPage = service.searchColorsByKey(key,page);
        model.addAttribute("listColor",listSearchByPage);
        return "color/HomepageColor";
    }
}
