package com.pick.nalsoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    
}