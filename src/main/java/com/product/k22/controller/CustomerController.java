package com.product.k22.controller;

import com.product.k22.model.Customer;
import com.product.k22.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/list")
    public String getList(Model model){
        ArrayList<Customer> list = customerService.getList();
        System.out.println(list.get(0).getId());
        model.addAttribute("list",list);
        return "customer/list";
    }
    @GetMapping("/create")
    public String create(Model model){
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
}
