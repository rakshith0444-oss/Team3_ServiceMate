package com.servicemate.servicemate.controller;

import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Home Page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Login Page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // Signup Page
    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // Signup — hashes password before saving
    @PostMapping("/signup")
    public String signup(User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already registered");
            return "signup";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }

    // Login — compares with hashed password
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User existingUser = userRepository.findByEmail(email);

        if (existingUser != null
                && existingUser.getPassword() != null
                && passwordEncoder.matches(password, existingUser.getPassword())) {
            session.setAttribute("loggedInUser", existingUser);
            model.addAttribute("user", existingUser);
            return "dashboard";
        }

        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}