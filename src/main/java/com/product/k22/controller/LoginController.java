package com.product.k22.controller;

import com.product.k22.model.Category;
import com.product.k22.model.Role;
import com.product.k22.model.User;
import com.product.k22.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/home")
    public String home(){
        return "index";
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
        List<Role> listRole = userService.getListRole();
        model.addAttribute("user",user);
        model.addAttribute("listRole",listRole);
        return "user/register";
    }
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute User user ,
                         @RequestParam  int[] roleIds){
        List<Role> roles;
        if (roleIds != null && roleIds.length >0){
            roles = new ArrayList<>();
            for (int id : roleIds) {
                Role role = userService.findRoleById(id);
                roles.add(role);
            }
            user.setRoles(roles);
        }
        userService.saveUser(user);
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
        String name = role.getRname();
        if (name != null && name.length()> 5){
            String nameTemp = name.substring(0,5);
            if (nameTemp.equals("ROLE_")){
                userService.saveRole(role);
                return "redirect:/login";
            }else {
                throw new RuntimeException("Role name invalid");
            }


        }else {
            throw new RuntimeException("Role name is null");
        }

    }
}
