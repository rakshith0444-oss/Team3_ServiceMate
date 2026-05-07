package com.servicemate.servicemate.controller;

import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.service.NotificationService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Notification Module:
 *  - List all notifications for the logged-in user
 *  - Mark all as read
 */
@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // ── Notification List ────────────────────────────────────────────────────
    @GetMapping
    public String listNotifications(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        // Mark all as read when user opens the page
        notificationService.markAllRead(user);

        model.addAttribute("notifications", notificationService.getForUser(user));
        model.addAttribute("user", user);
        model.addAttribute("unreadCount", 0L);
        return "notifications";
    }

    // ── Mark All Read (AJAX-friendly redirect) ────────────────────────────────
    @PostMapping("/mark-read")
    public String markAllRead(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) notificationService.markAllRead(user);
        return "redirect:/notifications";
    }
}
