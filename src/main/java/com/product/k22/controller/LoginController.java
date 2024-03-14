package com.product.k22.controller;

import com.product.k22.model.Category;
import com.product.k22.model.Role;
import com.product.k22.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String loginPost(@RequestParam String username,@RequestParam String password,
                            HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        ArrayList<Integer> list = new ArrayList<>();
        session.setAttribute("list",list);
        session.setMaxInactiveInterval(3000);
        return "redirect:/sale";
    }
    @GetMapping("/register")
    public String registerForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "user/register";
    }
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute User user){
        System.out.println(user.getName());
        return "redirect:/login";
    }
    @GetMapping("/register-role")
    public String registerFormRole(Model model){
        Role role = new Role();
        model.addAttribute("role",role);
        return "user/roleRegister";
    }
    @PostMapping("/register-role")
    public String registerRole(Model model, @ModelAttribute Role role){
        System.out.println(role.getRname());
        return "redirect:/login";
    }
}
