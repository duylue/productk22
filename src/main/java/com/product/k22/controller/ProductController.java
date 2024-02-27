package com.product.k22.controller;

import com.product.k22.model.Category;
import com.product.k22.model.Product;
import com.product.k22.service.CategoryService;
import com.product.k22.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/product")
@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String getList(Model model) {
//        ArrayList<Product> list = productService.getList();
        List<Map<String, Object>> list = productService.getListDetail();

        model.addAttribute("list", list);
        return "product/list";
    }
//    @GetMapping("/test")
//    public String test(Model model) {
//        List<Map<String, Object>> maps = productService.getListDetail();
//        model.addAttribute("list", maps);
//        return "product/list";
//    }

    @GetMapping("/save")
    public String save(Model model) {

        model.addAttribute("product", new Product());
        List<Category> cList = categoryService.getlist();
        model.addAttribute("cList", cList);

        return "/product/save";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute @Validated Product p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/product/save";
        }
        p.setSid(1);
        productService.save(p);
        return "redirect:/product/list";
    }

}
