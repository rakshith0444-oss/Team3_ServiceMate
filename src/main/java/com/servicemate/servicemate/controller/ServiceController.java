package com.servicemate.servicemate.controller;

import com.servicemate.servicemate.model.Booking;
import com.servicemate.servicemate.model.ServiceProvider;
import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.repository.BookingRepository;
import com.servicemate.servicemate.repository.ServiceProviderRepository;
import com.servicemate.servicemate.service.NotificationService;

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
    @Autowired private NotificationService notificationService;

    private User getUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    // ── ONE method handles ALL 23 services ───────────────────────
    private String servicePage(String category, String emoji, String title, String desc,
                                Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";
        List<ServiceProvider> providers = providerRepo.findByCategoryAndAvailable(category, true);
        model.addAttribute("providers", providers);
        model.addAttribute("serviceTitle", title);
        model.addAttribute("serviceEmoji", emoji);
        model.addAttribute("serviceDesc", desc);
        model.addAttribute("user", user);
        return "providers"; // ONE shared template!
    }

    // ── HOME SERVICES ─────────────────────────────────────────────
    @GetMapping("/services/ac-repair")
    public String acRepair(Model model, HttpSession session) {
        return servicePage("ac-repair", "❄️", "AC Repair & Servicing",
            "Certified technicians for all AC brands — installation, repair and servicing", model, session);
    }

    @GetMapping("/services/electrician")
    public String electrician(Model model, HttpSession session) {
        return servicePage("electrician", "⚡", "Electrician",
            "Certified electricians for wiring, repairs and installations", model, session);
    }

    @GetMapping("/services/plumber")
    public String plumber(Model model, HttpSession session) {
        return servicePage("plumber", "🚰", "Plumber",
            "Expert plumbers for pipe repairs, installations and water issues", model, session);
    }

    @GetMapping("/services/washing-machine")
    public String washingMachine(Model model, HttpSession session) {
        return servicePage("washing-machine", "🫧", "Washing Machine Repair",
            "Trained technicians for all washing machine brands and models", model, session);
    }

    @GetMapping("/services/refrigerator")
    public String refrigerator(Model model, HttpSession session) {
        return servicePage("refrigerator", "🧊", "Refrigerator Repair",
            "Fridge not cooling? Get it fixed by a certified technician", model, session);
    }

    @GetMapping("/services/tv-installation")
    public String tvInstallation(Model model, HttpSession session) {
        return servicePage("tv-installation", "📺", "TV Installation",
            "Professional TV mounting and installation for all screen sizes", model, session);
    }

    // ── CLEANING SERVICES ─────────────────────────────────────────
    @GetMapping("/services/deep-cleaning")
    public String deepCleaning(Model model, HttpSession session) {
        return servicePage("deep-cleaning", "🏠", "Home Deep Cleaning",
            "Thorough top-to-bottom home cleaning by trained professionals", model, session);
    }

    @GetMapping("/services/bathroom-cleaning")
    public String bathroomCleaning(Model model, HttpSession session) {
        return servicePage("bathroom-cleaning", "🚿", "Bathroom Cleaning",
            "Deep sanitization and cleaning for sparkling bathrooms", model, session);
    }

    @GetMapping("/services/sofa-cleaning")
    public String sofaCleaning(Model model, HttpSession session) {
        return servicePage("sofa-cleaning", "🛋️", "Sofa Cleaning",
            "Professional sofa and upholstery cleaning using safe products", model, session);
    }

    @GetMapping("/services/carpet-cleaning")
    public String carpetCleaning(Model model, HttpSession session) {
        return servicePage("carpet-cleaning", "🪣", "Carpet Cleaning",
            "Deep steam cleaning for carpets and rugs of all sizes", model, session);
    }

    @GetMapping("/services/kitchen-cleaning")
    public String kitchenCleaning(Model model, HttpSession session) {
        return servicePage("kitchen-cleaning", "👨‍🍳", "Kitchen Cleaning",
            "Grease removal and deep cleaning for your entire kitchen", model, session);
    }

    // ── PERSONAL SERVICES ─────────────────────────────────────────
    @GetMapping("/services/haircut")
    public String haircut(Model model, HttpSession session) {
        return servicePage("haircut", "💇", "Haircut at Home",
            "Professional stylists for haircuts and grooming at your doorstep", model, session);
    }

    @GetMapping("/services/makeup")
    public String makeup(Model model, HttpSession session) {
        return servicePage("makeup", "💄", "Makeup Artist",
            "Bridal, party and everyday makeup by certified artists", model, session);
    }

    @GetMapping("/services/spa")
    public String spa(Model model, HttpSession session) {
        return servicePage("spa", "💆", "Spa & Massage",
            "Relaxing spa treatments and massages in the comfort of your home", model, session);
    }

    @GetMapping("/services/fitness")
    public String fitness(Model model, HttpSession session) {
        return servicePage("fitness", "🏋️", "Fitness Trainer",
            "Personal trainers for weight loss, strength and flexibility", model, session);
    }

    // ── VEHICLE SERVICES ──────────────────────────────────────────
    @GetMapping("/services/bike-servicing")
    public String bikeServicing(Model model, HttpSession session) {
        return servicePage("bike-servicing", "🏍️", "Bike Servicing",
            "Complete bike servicing and repairs at your doorstep", model, session);
    }

    @GetMapping("/services/car-wash")
    public String carWash(Model model, HttpSession session) {
        return servicePage("car-wash", "🚿", "Car Washing",
            "Professional car wash and detailing at your location", model, session);
    }

    @GetMapping("/services/car-repair")
    public String carRepair(Model model, HttpSession session) {
        return servicePage("car-repair", "🔧", "Car Repair",
            "Trusted mechanics for all car repairs and maintenance", model, session);
    }

    @GetMapping("/services/battery-replacement")
    public String batteryReplacement(Model model, HttpSession session) {
        return servicePage("battery-replacement", "🔋", "Battery Replacement",
            "Quick battery replacement for bikes and cars at your doorstep", model, session);
    }

    // ── UTILITY SERVICES ──────────────────────────────────────────
    @GetMapping("/services/painting")
    public String painting(Model model, HttpSession session) {
        return servicePage("painting", "🎨", "Home Painting",
            "Professional painters for interior and exterior painting", model, session);
    }

    @GetMapping("/services/pest-control")
    public String pestControl(Model model, HttpSession session) {
        return servicePage("pest-control", "🐛", "Pest Control",
            "Safe and effective pest control for homes and offices", model, session);
    }

    @GetMapping("/services/carpenter")
    public String carpenter(Model model, HttpSession session) {
        return servicePage("carpenter", "🪚", "Carpenter Work",
            "Skilled carpenters for furniture, repairs and installations", model, session);
    }

    @GetMapping("/services/interior")
    public String interior(Model model, HttpSession session) {
        return servicePage("interior", "🛋️", "Interior Decoration",
            "Transform your space with professional interior designers", model, session);
    }

    // ── EMERGENCY SERVICES ────────────────────────────────────────
    @GetMapping("/services/emergency-electrician")
    public String emergencyElectrician(Model model, HttpSession session) {
        return servicePage("emergency-electrician", "⚡", "Emergency Electrician",
            "24/7 emergency electrical repairs — we respond in under 60 minutes", model, session);
    }

    @GetMapping("/services/emergency-plumber")
    public String emergencyPlumber(Model model, HttpSession session) {
        return servicePage("emergency-plumber", "🚰", "Emergency Plumber",
            "24/7 emergency plumbing — burst pipes, leaks, blocked drains", model, session);
    }

    @GetMapping("/services/locksmith")
    public String locksmith(Model model, HttpSession session) {
        return servicePage("locksmith", "🔐", "Lock Repair & Locksmith",
            "24/7 locksmith services — locked out? We'll be there fast", model, session);
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

        // Create in-app notification for the user
        String msg = String.format(
            "Booking confirmed with %s on %s at %s. We'll see you then! 🎉",
            provider.getName(), bookingDate, bookingTime
        );
        notificationService.create(user, msg, "BOOKING");

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
        model.addAttribute("unreadCount", notificationService.countUnread(user));
        return "my-bookings";
    }

    // ── CANCEL BOOKING (user self-service, only PENDING) ─────────────────────
    @PostMapping("/cancel-booking/{id}")
    public String cancelBooking(@PathVariable Long id, HttpSession session) {
        User user = getUser(session);
        if (user == null) return "redirect:/login";

        bookingRepo.findById(id).ifPresent(booking -> {
            // Security: only the booking owner can cancel, and only if PENDING
            if (booking.getUser().getId().equals(user.getId())
                    && "PENDING".equals(booking.getStatus())) {
                booking.setStatus("CANCELLED");
                bookingRepo.save(booking);
                notificationService.create(user,
                    "Your booking with " + booking.getProvider().getName() + " has been cancelled.",
                    "BOOKING");
            }
        });
        return "redirect:/my-bookings";
    }
}