package com.product.k22.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("name","duy123");
        cookie.setMaxAge(300);
        Cookie cookie2 = new Cookie("name2","duy1233");
        cookie2.setMaxAge(300);
        Cookie cookie3 = new Cookie("name3","duy12344");
        cookie3.setMaxAge(300);
        response.addCookie(cookie);
        response.addCookie(cookie2);
        response.addCookie(cookie3);
        return "index";
    }
}
