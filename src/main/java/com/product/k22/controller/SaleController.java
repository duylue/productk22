package com.product.k22.controller;

import com.product.k22.model.Product;
import com.product.k22.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/sale")
@Controller
public class SaleController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String list(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<Integer> list = (ArrayList<Integer>) session.getAttribute("list");
        if (list!=null){
            model.addAttribute("size",list.size());
        }
        List<Map<String,Object>> maps = productService.getListDetail();
        model.addAttribute("list",maps);
        return "sale/list";
    }

    @GetMapping("/add-cart")
    public String cartAdd(Model model, @RequestParam int pid,HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<Integer> list = (ArrayList<Integer>) session.getAttribute("list");
        if (list!= null){
            list.add(pid);
        }
        session.setAttribute("list",list);
        return "redirect:/sale";
    }

    @GetMapping("/cart")
    public String cart(Model model ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<Integer> list = (ArrayList<Integer>) session.getAttribute("list");
        ArrayList<Product> productList = new ArrayList<>();
        if (list.isEmpty()){
            for (int id: list) {
                Product product = productService.findById(id);
                productList.add(product);
            }
        }
        model.addAttribute("list",productList);
        return "/sale/cart";
    }
}
