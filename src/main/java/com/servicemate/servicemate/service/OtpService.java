package com.servicemate.servicemate.service;

import com.servicemate.servicemate.model.Otp;
import com.servicemate.servicemate.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Handles OTP lifecycle:
 *  1. Generate a 6-digit code, persist it, and email it.
 *  2. Validate the submitted code against the stored one.
 *  3. @Scheduled cleanup removes expired rows every 60 seconds.
 */
@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    private static final int OTP_EXPIRY_MINUTES = 5;
    private final SecureRandom random = new SecureRandom();

    /**
     * Generates, stores, and emails a fresh 6-digit OTP.
     * Any previous OTP for the same email is replaced.
     */
    public void generateAndSend(String email) {
        // Remove any existing OTP for this email
        otpRepository.deleteByEmail(email);

        // Generate 6-digit code
        String code = String.format("%06d", random.nextInt(1_000_000));

        // Persist with 5-minute expiry
        Otp otp = new Otp(email, code, LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
        otpRepository.save(otp);

        // Send email
        emailService.sendOtpEmail(email, code);
    }

    /**
     * Returns true if the submitted code matches the stored OTP AND has not expired.
     * Deletes the OTP row on successful validation (one-time use).
     */
    public boolean validateOtp(String email, String submittedCode) {
        Optional<Otp> stored = otpRepository.findByEmail(email);
        if (stored.isEmpty()) return false;

        Otp otp = stored.get();
        boolean valid = otp.getOtpCode().equals(submittedCode)
                && LocalDateTime.now().isBefore(otp.getExpiresAt());

        if (valid) {
            otpRepository.deleteByEmail(email); // one-time use
        }
        return valid;
    }

    /**
     * Runs every 60 seconds to delete expired OTP rows from the database.
     * This is the "OTP Expiration" item from the project document.
     */
    @Scheduled(fixedRate = 60_000)
    public void cleanUpExpiredOtps() {
        otpRepository.deleteByExpiresAtBefore(LocalDateTime.now());
    }
}
