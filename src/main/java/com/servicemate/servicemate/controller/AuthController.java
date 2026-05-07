package com.servicemate.servicemate.controller;

import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.repository.UserRepository;
import com.servicemate.servicemate.service.NotificationService;
import com.servicemate.servicemate.service.OtpService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handles all authentication flows:
 *  - Signup (with BCrypt password hashing)
 *  - Login Step 1: email submitted → OTP sent via email
 *  - Login Step 2: OTP verification → session created
 *  - Logout
 */
@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @Autowired
    private NotificationService notificationService;

    // ── Home Page ────────────────────────────────────────────────────────────
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // ── Dashboard ─────────────────────────────────────────────────────────────
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        model.addAttribute("unreadCount", notificationService.countUnread(user));
        return "dashboard";
    }

    // ── Login Step 1: show email entry form ─────────────────────────────────
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    /**
     * Login Step 1 — POST:
     * Check the email exists, then send OTP and redirect to verify page.
     */
    @PostMapping("/login")
    public String initiateLogin(@RequestParam String email,
                                HttpSession session,
                                Model model) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            model.addAttribute("error", "No account found with that email. Please sign up first.");
            return "login";
        }

        // Store email in session so the verify step can look up the user
        session.setAttribute("otpEmail", email);

        try {
            otpService.generateAndSend(email);
        } catch (Exception e) {
            model.addAttribute("error",
                "Could not send OTP. Please check that MAIL_USERNAME and MAIL_PASSWORD env vars are set correctly.");
            return "login";
        }

        return "redirect:/verify-otp";
    }

    // ── Login Step 2: OTP verification form ─────────────────────────────────
    @GetMapping("/verify-otp")
    public String showOtpForm(HttpSession session, Model model) {
        String email = (String) session.getAttribute("otpEmail");
        if (email == null) return "redirect:/login";
        model.addAttribute("email", email);
        return "otp-verify";
    }

    /**
     * Login Step 2 — POST: validate OTP.
     * On success → create session and send to dashboard.
     * On failure → back to OTP form with error.
     */
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String otp,
                            HttpSession session,
                            Model model) {
        String email = (String) session.getAttribute("otpEmail");
        if (email == null) return "redirect:/login";

        if (!otpService.validateOtp(email, otp)) {
            model.addAttribute("email", email);
            model.addAttribute("error", "Invalid or expired OTP. Please try again.");
            return "otp-verify";
        }

        // OTP valid — complete login
        User user = userRepository.findByEmail(email);
        session.removeAttribute("otpEmail");
        session.setAttribute("loggedInUser", user);
        model.addAttribute("user", user);
        model.addAttribute("unreadCount", notificationService.countUnread(user));
        return "dashboard";
    }

    // ── Resend OTP ──────────────────────────────────────────────────────────
    @GetMapping("/resend-otp")
    public String resendOtp(HttpSession session) {
        String email = (String) session.getAttribute("otpEmail");
        if (email == null) return "redirect:/login";
        try {
            otpService.generateAndSend(email);
        } catch (Exception ignored) {}
        return "redirect:/verify-otp?resent=true";
    }

    // ── Signup ───────────────────────────────────────────────────────────────
    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already registered");
            return "signup";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login?registered=true";
    }

    // ── Logout ───────────────────────────────────────────────────────────────
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}