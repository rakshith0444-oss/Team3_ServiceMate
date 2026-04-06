package com.servicemate.servicemate.repository;

import com.servicemate.servicemate.model.Booking;
import com.servicemate.servicemate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Get all bookings for a specific user (for "My Bookings" page)
    List<Booking> findByUser(User user);

    // Get bookings by status e.g. "PENDING"
    List<Booking> findByStatus(String status);

}