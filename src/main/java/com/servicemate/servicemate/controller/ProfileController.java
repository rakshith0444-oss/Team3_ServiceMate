package com.servicemate.servicemate.controller;

import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.repository.UserRepository;
import com.servicemate.servicemate.service.NotificationService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles the User Profile module:
 *  - View profile  (GET /profile)
 *  - Update name/phone (POST /profile/update)
 *  - Change password   (POST /profile/change-password)
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private NotificationService notificationService;

    // ── View Profile ─────────────────────────────────────────────────────────
    @GetMapping
    public String viewProfile(Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";

        // Refresh from DB to get latest data
        user = userRepository.findById(user.getId()).orElse(user);
        session.setAttribute("loggedInUser", user);

        model.addAttribute("user", user);
        model.addAttribute("unreadCount", notificationService.countUnread(user));
        return "profile";
    }

    // ── Update name & phone ──────────────────────────────────────────────────
    @PostMapping("/update")
    public String updateProfile(@RequestParam String fullName,
                                @RequestParam String phone,
                                HttpSession session,
                                RedirectAttributes ra) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";

        user.setFullName(fullName);
        user.setPhone(phone);
        userRepository.save(user);
        session.setAttribute("loggedInUser", user);

        ra.addFlashAttribute("success", "Profile updated successfully!");
        return "redirect:/profile";
    }

    // ── Change Password ───────────────────────────────────────────────────────
    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpSession session,
                                 RedirectAttributes ra) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";

        // Validate current password (BCrypt)
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            ra.addFlashAttribute("pwdError", "Current password is incorrect.");
            return "redirect:/profile";
        }

        if (!newPassword.equals(confirmPassword)) {
            ra.addFlashAttribute("pwdError", "New passwords do not match.");
            return "redirect:/profile";
        }

        if (newPassword.length() < 6) {
            ra.addFlashAttribute("pwdError", "New password must be at least 6 characters.");
            return "redirect:/profile";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        session.setAttribute("loggedInUser", user);

        ra.addFlashAttribute("pwdSuccess", "Password changed successfully!");
        return "redirect:/profile";
    }

    private User getUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }
}
