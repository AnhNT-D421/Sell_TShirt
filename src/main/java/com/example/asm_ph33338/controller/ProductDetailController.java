package com.example.asm_ph33338.controller;

import com.example.asm_ph33338.entity.ColorEntity;
import com.example.asm_ph33338.entity.ProductDetailEntity;
import com.example.asm_ph33338.entity.ProductEntity;
import com.example.asm_ph33338.entity.SizeEntity;
import com.example.asm_ph33338.service.ColorService;
import com.example.asm_ph33338.service.ProductDetailService;
import com.example.asm_ph33338.service.ProductService;
import com.example.asm_ph33338.service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product-detail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailSer;
    @Autowired
    private ProductService productSer;
    @Autowired
    private SizeService sizeSer;
    @Autowired
    private ColorService colorSer;

    @GetMapping("/home")
    public String getFormHome(@RequestParam(name = "page", defaultValue = "0") int page,
                              Model model) {
        Page<ProductDetailEntity> listProductDetail = productDetailSer.getAllProductDetailByPage(page);
        model.addAttribute("listProductDetail", listProductDetail);
        return "product-detail/HomepageProductDetail";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductDetail(@PathVariable("id") Integer id, RedirectAttributes ra) {
        productDetailSer.deteleProductDetail(id);
        ra.addFlashAttribute("messages", "Delete successfully product detail with ID: " + id);
        return "redirect:/product-detail/home";
    }

    @ModelAttribute("products")
    public List<ProductEntity> getDataProducts() {
        List<ProductEntity> listProduct = productSer.getAllProductNonPaging();
        return listProduct;
    }

    @ModelAttribute("sizes")
    public List<SizeEntity> getDataSizes() {
        List<SizeEntity> listSize = sizeSer.getAllSizeNonPaging();
        return listSize;
    }

    @ModelAttribute("colors")
    public List<ColorEntity> getDataColors() {
        List<ColorEntity> listColor = colorSer.getAllColorNonPaging();
        return listColor;
    }

    @GetMapping("/create")
    public String getFormCreate(Model model) {
        model.addAttribute("productDetailNew", new ProductDetailEntity());
        return "product-detail/CreateProductDetail";
    }

    @PostMapping("/create")
    public String createNewProductDetail(@Valid @ModelAttribute("productDetailNew") ProductDetailEntity productDetail,
                                         BindingResult result, RedirectAttributes ra) throws IOException {
        if (result.hasErrors()) {
            return "product-detail/CreateProductDetail";
        }
//        if(!multipartFile.isEmpty()){
//            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            productDetail.setImageFileName(fileName);
//            String upload = "images/"+productDetail.getId();
//            FileUpload.saveFile(upload,fileName,multipartFile);
//        }else {
//            if(productDetail.getImageFileName().isEmpty()){
//                productDetail.setImageFileName(null);
//                productDetailSer.craeteProductDetail(productDetail);
//            }
//        }
        productDetailSer.craeteProductDetail(productDetail);
        ra.addFlashAttribute("messages", "Create successfully new product detail");
        return "redirect:/product-detail/home";
    }

    @GetMapping("/detail/{id}")
    public String getFormUpdate(@PathVariable("id") Integer id, Model model) {
        ProductDetailEntity productDetail = productDetailSer.findProductDetailByID(id);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("title", "Update Product Detail With ID: " + id);
        return "product-detail/UpdateProductDetail";
    }

    @PostMapping("/update")
    public String updateProductDetail(@Valid @ModelAttribute("productDetail") ProductDetailEntity productDetail, BindingResult result,
                                      RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "product-detail/UpdateProductDetail";
        }
        productDetailSer.updateProductDetail(productDetail);
        ra.addFlashAttribute("messages", "Update successfully this product detail with ID :" + productDetail.getId());
        return "redirect:/product-detail/home";
    }

    @GetMapping("/search")
    public String searchProductDetailByKey(@RequestParam("key") String key,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           Model model) {
        Page<ProductDetailEntity> listSearchByKey = productDetailSer.searchProductDetailByKey(key, page);
        model.addAttribute("listProductDetail", listSearchByKey);
        return "product-detail/HomepageProductDetail";
    }


}
