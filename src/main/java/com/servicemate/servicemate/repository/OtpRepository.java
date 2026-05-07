package com.servicemate.servicemate.repository;

import com.servicemate.servicemate.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    /** Called by OtpService scheduler to purge rows with past expiry. */
    @Transactional
    void deleteByExpiresAtBefore(LocalDateTime now);
}
