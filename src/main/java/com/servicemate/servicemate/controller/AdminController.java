package com.servicemate.servicemate.controller;

import com.servicemate.servicemate.model.ServiceProvider;
import com.servicemate.servicemate.repository.BookingRepository;
import com.servicemate.servicemate.repository.ServiceProviderRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private ServiceProviderRepository providerRepo;
    @Autowired private BookingRepository bookingRepo;

    // ── Admin dashboard ──────────────────────────────────────────
    @GetMapping
    public String adminHome(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("providers", providerRepo.findAll());
        model.addAttribute("bookings", bookingRepo.findAll());
        model.addAttribute("newProvider", new ServiceProvider());
        return "admin";
    }

    // ── Add a new provider ───────────────────────────────────────
    @PostMapping("/add-provider")
    public String addProvider(@ModelAttribute ServiceProvider provider, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        provider.setAvailable(true);
        providerRepo.save(provider);
        return "redirect:/admin";
    }

    // ── Delete a provider ────────────────────────────────────────
    @GetMapping("/delete-provider/{id}")
    public String deleteProvider(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        providerRepo.deleteById(id);
        return "redirect:/admin";
    }

    // ── Toggle availability ──────────────────────────────────────
    @GetMapping("/toggle-provider/{id}")
    public String toggleProvider(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        providerRepo.findById(id).ifPresent(p -> {
            p.setAvailable(!p.isAvailable());
            providerRepo.save(p);
        });
        return "redirect:/admin";
    }

    // ── Simple admin check — user with role "admin" ──────────────
    private boolean isAdmin(HttpSession session) {
        var user = (com.servicemate.servicemate.model.User) session.getAttribute("loggedInUser");
        return user != null && "admin".equals(user.getRole());
    }
}