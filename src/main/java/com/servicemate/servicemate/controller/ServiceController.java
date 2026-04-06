package com.servicemate.servicemate.controller;

import com.servicemate.servicemate.model.Booking;
import com.servicemate.servicemate.model.ServiceProvider;
import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.repository.BookingRepository;
import com.servicemate.servicemate.repository.ServiceProviderRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class ServiceController {

    @Autowired private ServiceProviderRepository providerRepo;
    @Autowired private BookingRepository bookingRepo;

    // ── Helper to avoid repeating session check ──────────────────
    private User getUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    private String serviceRoute(String category, String template, Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";
        List<ServiceProvider> providers = providerRepo.findByCategoryAndAvailable(category, true);
        model.addAttribute("providers", providers);
        model.addAttribute("user", user);
        return template;
    }

    // ── HOME SERVICES ─────────────────────────────────────────────

    @GetMapping("/services/ac-repair")
    public String acRepair(Model model, HttpSession session) {
        return serviceRoute("ac-repair", "providers-ac-repair", model, session);
    }

    @GetMapping("/services/electrician")
    public String electrician(Model model, HttpSession session) {
        return serviceRoute("electrician", "providers-electrician", model, session);
    }

    @GetMapping("/services/plumber")
    public String plumber(Model model, HttpSession session) {
        return serviceRoute("plumber", "providers-plumber", model, session);
    }

    @GetMapping("/services/washing-machine")
    public String washingMachine(Model model, HttpSession session) {
        return serviceRoute("washing-machine", "providers-washing-machine", model, session);
    }

    @GetMapping("/services/refrigerator")
    public String refrigerator(Model model, HttpSession session) {
        return serviceRoute("refrigerator", "providers-refrigerator", model, session);
    }

    @GetMapping("/services/tv-installation")
    public String tvInstallation(Model model, HttpSession session) {
        return serviceRoute("tv-installation", "providers-tv-installation", model, session);
    }

    // ── CLEANING SERVICES ─────────────────────────────────────────

    @GetMapping("/services/deep-cleaning")
    public String deepCleaning(Model model, HttpSession session) {
        return serviceRoute("deep-cleaning", "providers-deep-cleaning", model, session);
    }

    @GetMapping("/services/bathroom-cleaning")
    public String bathroomCleaning(Model model, HttpSession session) {
        return serviceRoute("bathroom-cleaning", "providers-bathroom-cleaning", model, session);
    }

    @GetMapping("/services/sofa-cleaning")
    public String sofaCleaning(Model model, HttpSession session) {
        return serviceRoute("sofa-cleaning", "providers-sofa-cleaning", model, session);
    }

    @GetMapping("/services/carpet-cleaning")
    public String carpetCleaning(Model model, HttpSession session) {
        return serviceRoute("carpet-cleaning", "providers-carpet-cleaning", model, session);
    }

    @GetMapping("/services/kitchen-cleaning")
    public String kitchenCleaning(Model model, HttpSession session) {
        return serviceRoute("kitchen-cleaning", "providers-kitchen-cleaning", model, session);
    }

    // ── PERSONAL SERVICES ─────────────────────────────────────────

    @GetMapping("/services/haircut")
    public String haircut(Model model, HttpSession session) {
        return serviceRoute("haircut", "providers-haircut", model, session);
    }

    @GetMapping("/services/makeup")
    public String makeup(Model model, HttpSession session) {
        return serviceRoute("makeup", "providers-makeup", model, session);
    }

    @GetMapping("/services/spa")
    public String spa(Model model, HttpSession session) {
        return serviceRoute("spa", "providers-spa", model, session);
    }

    @GetMapping("/services/fitness")
    public String fitness(Model model, HttpSession session) {
        return serviceRoute("fitness", "providers-fitness", model, session);
    }

    // ── VEHICLE SERVICES ──────────────────────────────────────────

    @GetMapping("/services/bike-servicing")
    public String bikeServicing(Model model, HttpSession session) {
        return serviceRoute("bike-servicing", "providers-bike-servicing", model, session);
    }

    @GetMapping("/services/car-wash")
    public String carWash(Model model, HttpSession session) {
        return serviceRoute("car-wash", "providers-car-wash", model, session);
    }

    @GetMapping("/services/car-repair")
    public String carRepair(Model model, HttpSession session) {
        return serviceRoute("car-repair", "providers-car-repair", model, session);
    }

    @GetMapping("/services/battery-replacement")
    public String batteryReplacement(Model model, HttpSession session) {
        return serviceRoute("battery-replacement", "providers-battery-replacement", model, session);
    }

    // ── UTILITY SERVICES ──────────────────────────────────────────

    @GetMapping("/services/painting")
    public String painting(Model model, HttpSession session) {
        return serviceRoute("painting", "providers-painting", model, session);
    }

    @GetMapping("/services/pest-control")
    public String pestControl(Model model, HttpSession session) {
        return serviceRoute("pest-control", "providers-pest-control", model, session);
    }

    @GetMapping("/services/carpenter")
    public String carpenter(Model model, HttpSession session) {
        return serviceRoute("carpenter", "providers-carpenter", model, session);
    }

    @GetMapping("/services/interior")
    public String interior(Model model, HttpSession session) {
        return serviceRoute("interior", "providers-interior", model, session);
    }

    // ── EMERGENCY SERVICES ────────────────────────────────────────

    @GetMapping("/services/emergency-electrician")
    public String emergencyElectrician(Model model, HttpSession session) {
        return serviceRoute("emergency-electrician", "providers-emergency-electrician", model, session);
    }

    @GetMapping("/services/emergency-plumber")
    public String emergencyPlumber(Model model, HttpSession session) {
        return serviceRoute("emergency-plumber", "providers-emergency-plumber", model, session);
    }

    @GetMapping("/services/locksmith")
    public String locksmith(Model model, HttpSession session) {
        return serviceRoute("locksmith", "providers-locksmith", model, session);
    }

    // ── PROVIDER PROFILE ──────────────────────────────────────────
    @GetMapping("/provider/{id}")
    public String providerProfile(@PathVariable Long id, Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";
        ServiceProvider provider = providerRepo.findById(id).orElse(null);
        if (provider == null) return "redirect:/dashboard";
        model.addAttribute("provider", provider);
        model.addAttribute("user", user);
        return "provider-profile";
    }

    // ── BOOKING FORM ──────────────────────────────────────────────
    @GetMapping("/book/{providerId}")
    public String showBookingForm(@PathVariable Long providerId, Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";
        ServiceProvider provider = providerRepo.findById(providerId).orElse(null);
        if (provider == null) return "redirect:/dashboard";
        model.addAttribute("provider", provider);
        model.addAttribute("user", user);
        model.addAttribute("today", LocalDate.now().toString());
        return "booking-form";
    }

    // ── CONFIRM BOOKING ───────────────────────────────────────────
    @PostMapping("/book/{providerId}")
    public String confirmBooking(@PathVariable Long providerId,
                                 @RequestParam String bookingDate,
                                 @RequestParam String bookingTime,
                                 @RequestParam String address,
                                 @RequestParam(required = false) String notes,
                                 HttpSession session, Model model) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";
        ServiceProvider provider = providerRepo.findById(providerId).orElse(null);
        if (provider == null) return "redirect:/dashboard";

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setProvider(provider);
        booking.setBookingDate(LocalDate.parse(bookingDate));
        booking.setBookingTime(LocalTime.parse(bookingTime));
        booking.setAddress(address);
        booking.setNotes(notes);
        booking.setStatus("PENDING");
        bookingRepo.save(booking);

        model.addAttribute("user", user);
        model.addAttribute("booking", booking);
        return "booking-confirmed";
    }

    // ── MY BOOKINGS ───────────────────────────────────────────────
    @GetMapping("/my-bookings")
    public String myBookings(Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";
        model.addAttribute("bookings", bookingRepo.findByUser(user));
        model.addAttribute("user", user);
        return "my-bookings";
    }
}