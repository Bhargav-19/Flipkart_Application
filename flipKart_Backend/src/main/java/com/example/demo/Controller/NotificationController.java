package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.NotificationService;
import com.example.demo.model.Notification;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/create")
	public Notification create(@RequestParam Long userId, @RequestParam String title,
			@RequestParam String message, @RequestParam(required = false) String type) {
		return notificationService.createNotification(userId, title, message, type);
	}

	@GetMapping("/user/{userId}")
	public List<Notification> listForUser(@PathVariable Long userId) {
		return notificationService.getNotificationsForUser(userId);
	}

	@GetMapping("/user/{userId}/unread-count")
	public long unreadCount(@PathVariable Long userId) {
		return notificationService.getUnreadCount(userId);
	}

	@PutMapping("/{notificationId}/read")
	public Notification markRead(@PathVariable Long notificationId, @RequestParam Long userId) {
		return notificationService.markAsRead(notificationId, userId);
	}

	@PutMapping("/user/{userId}/read-all")
	public String markAllRead(@PathVariable Long userId) {
		int updated = notificationService.markAllAsRead(userId);
		return "Marked " + updated + " notification(s) as read.";
	}

	@DeleteMapping("/{notificationId}")
	public String delete(@PathVariable Long notificationId, @RequestParam Long userId) {
		notificationService.deleteNotification(notificationId, userId);
		return "Notification deleted.";
	}
}
