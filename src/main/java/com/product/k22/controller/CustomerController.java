package com.product.k22.controller;

import com.product.k22.model.Customer;
import com.product.k22.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/list")
    public String getList(Model model){
        ArrayList<Customer> list = customerService.getList();
        model.addAttribute("list",list);

        return "customer/list";
    }
    @GetMapping("/create")
    public String create(Model model){
        List<Map<String, Object>> addList = customerService.getListAddress();
        model.addAttribute("aList",addList);
        model.addAttribute("customer",new Customer());
        return "customer/create";
    }
    @GetMapping("/update")
    public String update(Model model, @RequestParam int id){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer",customer);
        return "customer/create";
    }
    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Customer customer){
        customerService.create(customer);
        return "redirect:/customer/list";
    }
    @GetMapping("/detail")
    public String detail(Model model, @RequestParam int id){
      List<Map<String,Object>> list = customerService.getCusDetail(id);
        model.addAttribute("list",list);
        return "customer/detail";
    }
}
