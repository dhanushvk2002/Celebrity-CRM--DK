package com.celebritycrm.notifications.service;

import com.celebritycrm.common.exception.ResourceNotFoundException;
import com.celebritycrm.notifications.entity.Notification;
import com.celebritycrm.notifications.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public Notification sendNotification(String recipientEmail, String title, String message) {
        Notification notification = Notification.builder()
                .recipientEmail(recipientEmail)
                .title(title)
                .message(message)
                .isRead(false)
                .build();
        return notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<Notification> getNotificationsForUser(String recipientEmail) {
        return notificationRepository.findByRecipientEmailOrderByCreatedAtDesc(recipientEmail);
    }

    @Transactional(readOnly = true)
    public List<Notification> getUnreadNotifications(String recipientEmail) {
        return notificationRepository.findByRecipientEmailAndIsReadFalse(recipientEmail);
    }

    @Transactional
    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}

