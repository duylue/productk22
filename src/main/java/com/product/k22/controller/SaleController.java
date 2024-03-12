package com.product.k22.controller;

import com.product.k22.dto.ProductDTO;
import com.product.k22.model.Category;
import com.product.k22.model.Product;
import com.product.k22.repository.CategoryRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping
    public String list(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<Integer> list = (ArrayList<Integer>) session.getAttribute("list");
        int size = 0;
        if (list!=null){
        size = list.size();
        }
        List<Category> clist = categoryRepository.findAll();
        model.addAttribute("size",size);
        model.addAttribute("cList",clist);
        List<ProductDTO> productDTOList = productService.getProductDetail(0,"",0);
        model.addAttribute("list",productDTOList);
        return "sale/list";
    }
    @GetMapping("/search")
    public String search(Model model, HttpServletRequest request,@RequestParam int cid,
                         @RequestParam String pname, @RequestParam int priceId) {
        HttpSession session = request.getSession();
        ArrayList<Integer> list = (ArrayList<Integer>) session.getAttribute("list");
        int size = 0;
        if (list!=null){
            size = list.size();
        }
        List<Category> clist = categoryRepository.findAll();
        model.addAttribute("size",size);
        model.addAttribute("cList",clist);
        List<ProductDTO> productDTOList = productService.getProductDetail(cid,pname,priceId);
        model.addAttribute("list",productDTOList);
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
//AVC
    @GetMapping("/cart")
    public String cart(Model model ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<Integer> list = (ArrayList<Integer>) session.getAttribute("list");
        ArrayList<Map<String, Object>> productList = new ArrayList<>();
        if (list!= null && !list.isEmpty()){
            for (int id: list) {
                Map<String, Object> map= productService.getProductDetail(id);
                productList.add(map);
            }
        }
        model.addAttribute("list",productList);
        return "/sale/cart";
    }
}
