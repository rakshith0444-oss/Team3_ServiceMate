package com.servicemate.servicemate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.service.AuthService;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    // OPEN LOGIN PAGE
    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("message", "");
        return "login";
    }

    // OPEN SIGNUP PAGE
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    // HANDLE SIGNUP
    @PostMapping("/signup")
    public String signup(User user) {

        authService.registerUser(user);

        return "login";
    }

    // HANDLE LOGIN
    @PostMapping("/login")
    public String login(User user, Model model) {

        User loggedUser = authService.loginUser(user.getEmail(), user.getPassword());

        if (loggedUser != null) {
            model.addAttribute("message", "Login Successful!");
        } 
        else {
            model.addAttribute("message", "Invalid Email or Password");
        }

        return "login";
    }
}