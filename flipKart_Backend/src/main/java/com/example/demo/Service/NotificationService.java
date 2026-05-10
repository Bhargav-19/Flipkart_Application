package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repository.INotificationRepository;
import com.example.demo.Repository.IregisterRepo;
import com.example.demo.model.Notification;
import com.example.demo.model.Register;

@Service
public class NotificationService {

	@Autowired
	private INotificationRepository notificationRepository;

	@Autowired
	private IregisterRepo userRepository;

	public Notification createNotification(Long userId, String title, String message, String type) {
		Register user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		Notification n = new Notification();
		n.setUser(user);
		n.setTitle(title);
		n.setMessage(message);
		n.setType(type);
		n.setReadFlag(false);
		n.setCreatedAt(LocalDateTime.now());
		return notificationRepository.save(n);
	}

	public List<Notification> getNotificationsForUser(Long userId) {
		Register user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return notificationRepository.findByUserOrderByCreatedAtDesc(user);
	}

	public long getUnreadCount(Long userId) {
		Register user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return notificationRepository.countByUserAndReadFlagFalse(user);
	}

	@Transactional
	public Notification markAsRead(Long notificationId, Long userId) {
		Notification n = notificationRepository.findById(notificationId)
				.orElseThrow(() -> new RuntimeException("Notification not found"));
		if (!n.getUser().getId().equals(userId)) {
			throw new RuntimeException("Notification does not belong to user");
		}
		n.setReadFlag(true);
		return notificationRepository.save(n);
	}

	@Transactional
	public int markAllAsRead(Long userId) {
		Register user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return notificationRepository.markAllReadForUser(user);
	}

	@Transactional
	public void deleteNotification(Long notificationId, Long userId) {
		Notification n = notificationRepository.findById(notificationId)
				.orElseThrow(() -> new RuntimeException("Notification not found"));
		if (!n.getUser().getId().equals(userId)) {
			throw new RuntimeException("Notification does not belong to user");
		}
		notificationRepository.delete(n);
	}
}
