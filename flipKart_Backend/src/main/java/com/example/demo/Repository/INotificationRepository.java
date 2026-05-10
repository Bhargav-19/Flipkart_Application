package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Notification;
import com.example.demo.model.Register;

public interface INotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByUserOrderByCreatedAtDesc(Register user);

	long countByUserAndReadFlagFalse(Register user);

	@Modifying
	@Query("UPDATE Notification n SET n.readFlag = true WHERE n.user = :user AND n.readFlag = false")
	int markAllReadForUser(@Param("user") Register user);
}
