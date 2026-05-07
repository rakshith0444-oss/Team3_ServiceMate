package com.servicemate.servicemate.controller;

import com.servicemate.servicemate.model.Booking;
import com.servicemate.servicemate.model.Notification;
import com.servicemate.servicemate.model.ServiceProvider;
import com.servicemate.servicemate.repository.BookingRepository;
import com.servicemate.servicemate.repository.ServiceProviderRepository;
import com.servicemate.servicemate.repository.UserRepository;
import com.servicemate.servicemate.service.NotificationService;

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
    @Autowired private UserRepository userRepo;
    @Autowired private NotificationService notificationService;

    // ── Admin dashboard ──────────────────────────────────────────────────────
    @GetMapping
    public String adminHome(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("providers", providerRepo.findAll());
        model.addAttribute("bookings", bookingRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("newProvider", new ServiceProvider());
        return "admin";
    }

    // ── Add a new provider ───────────────────────────────────────────────────
    @PostMapping("/add-provider")
    public String addProvider(@ModelAttribute ServiceProvider provider, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        provider.setAvailable(true);
        providerRepo.save(provider);
        return "redirect:/admin";
    }

    // ── Delete a provider ────────────────────────────────────────────────────
    @GetMapping("/delete-provider/{id}")
    public String deleteProvider(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        providerRepo.deleteById(id);
        return "redirect:/admin";
    }

    // ── Toggle availability ──────────────────────────────────────────────────
    @GetMapping("/toggle-provider/{id}")
    public String toggleProvider(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        providerRepo.findById(id).ifPresent(p -> {
            p.setAvailable(!p.isAvailable());
            providerRepo.save(p);
        });
        return "redirect:/admin";
    }

    // ── Edit provider details ────────────────────────────────────────────────
    @PostMapping("/edit-provider/{id}")
    public String editProvider(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam String phone,
                               @RequestParam String location,
                               @RequestParam String experience,
                               @RequestParam double pricePerHour,
                               @RequestParam double rating,
                               @RequestParam int reviewCount,
                               @RequestParam String description,
                               HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        providerRepo.findById(id).ifPresent(p -> {
            p.setName(name);
            p.setPhone(phone);
            p.setLocation(location);
            p.setExperience(experience);
            p.setPricePerHour(pricePerHour);
            p.setRating(rating);
            p.setReviewCount(reviewCount);
            p.setDescription(description);
            providerRepo.save(p);
        });
        return "redirect:/admin";
    }

    // ── Update booking status (CONFIRMED / COMPLETED / CANCELLED) ────────────
    @PostMapping("/update-booking-status/{id}")
    public String updateBookingStatus(@PathVariable Long id,
                                      @RequestParam String status,
                                      HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        bookingRepo.findById(id).ifPresent(booking -> {
            String oldStatus = booking.getStatus();
            booking.setStatus(status);
            bookingRepo.save(booking);

            // Notify the user about the status change
            String msg = String.format(
                "Your booking with %s has been updated: %s → %s",
                booking.getProvider().getName(), oldStatus, status
            );
            notificationService.create(booking.getUser(), msg, "BOOKING");
        });

        return "redirect:/admin";
    }

    // ── Admin check helper ───────────────────────────────────────────────────
    private boolean isAdmin(HttpSession session) {
        var user = (com.servicemate.servicemate.model.User) session.getAttribute("loggedInUser");
        return user != null && "admin".equals(user.getRole());
    }
}