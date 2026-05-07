package com.servicemate.servicemate.service;

import com.servicemate.servicemate.model.Notification;
import com.servicemate.servicemate.model.User;
import com.servicemate.servicemate.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    /** Creates and persists a new notification for the given user. */
    public Notification create(User user, String message, String type) {
        return notificationRepository.save(new Notification(user, message, type));
    }

    /** Returns all notifications for a user, newest first. */
    public List<Notification> getForUser(User user) {
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    /** Returns count of unread notifications for the user. */
    public long countUnread(User user) {
        return notificationRepository.countByUserAndRead(user, false);
    }

    /** Marks every notification for the user as read. */
    public void markAllRead(User user) {
        notificationRepository.markAllReadByUser(user);
    }
}
