package com.product.k22.controller;

import com.product.k22.model.Category;
import com.product.k22.model.Product;
import com.product.k22.service.CategoryService;
import com.product.k22.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Map<String, Object> map = categoryService.getProductDetail(1,1);
        ArrayList<Product> list = productService.getList();
        List<Map<String, Object>> maps = productService.getListDetail();
        model.addAttribute("list", maps);
        return "product/list";
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("product", new Product());
        List<Category> cList = categoryService.getlist();
        model.addAttribute("cList", cList);
        return "/product/save";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product p) {
        p.setStatusId(1);
        productService.save(p);
        return "redirect:/product/list";
    }

}
