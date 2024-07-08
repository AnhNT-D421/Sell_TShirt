package com.example.asm_ph33338.controller;

import com.example.asm_ph33338.entity.ProductEntity;
import com.example.asm_ph33338.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/home")
    public String getFormHomepageProduct(@RequestParam(name = "page", defaultValue = "0") int page,
                                         Model model) {
        Page<ProductEntity> listProduct = service.getAllProductByPage(page);
        model.addAttribute("listProduct",listProduct);
        return "product/HomepageProduct";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deteleProduct(id);
        ra.addFlashAttribute("messages", "Delete successfully product with ID: " + id);
        return "redirect:/product/home";
    }

    @GetMapping("/craete")
    public String getFormCreate(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "product/CreateProduct";
    }

    @PostMapping("/create")
    public String createNewCustomer(@Valid @ModelAttribute("product") ProductEntity product, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "product/CreateProduct";
        }
        service.craeteProduct(product);
        ra.addFlashAttribute("messages", "Craete new product successfully");
        return "redirect:/product/home";
    }

    @GetMapping("/detail/{id}")
    public String getFormUpdate(@PathVariable("id") Integer id, Model model) {
        ProductEntity productDetail = service.findProductByID(id);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("title", "Update Product With ID: " + id);
        return "product/UpdateProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("productDetail") ProductEntity product, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "product/UpdateProduct";
        }
        service.updateProduct(product);
        ra.addFlashAttribute("messages", "Update successfully this product");
        return "redirect:/product/home";
    }
    @GetMapping("/search")
    public String searchProduct(@RequestParam("key") String key,
                                @RequestParam(name = "page",defaultValue = "0")int page,
                                Model model ){
        Page<ProductEntity> listKeyByPage = service.searchProductPageByName(key,page);
        model.addAttribute("listProduct",listKeyByPage);
        return "product/HomepageProduct";
    }
}
