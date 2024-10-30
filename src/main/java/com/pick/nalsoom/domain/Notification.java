package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.NotificationDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NOTIFICATION")
@Builder
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTIFICATION_PROPER_NUM")
    private Long notificationProperNum;

    @Column(name = "NOTIFICATION_CONTENT")
    private String notificationContent;

    @Column(name = "NOTIFICATION_TYPE")
    private String notificationType;

    @Column(name = "NOTIFICATION_READ_YN")
    private String notificationReadYn;

    @Column(name = "USER_PROPER_NUM")
    private String userProperNum;

    public NotificationDto toDto() {
        return NotificationDto.builder()
                .notificationProperNum(notificationProperNum)
                .notificationContent(notificationContent)
                .notificationType(notificationType)
                .notificationReadYn(notificationReadYn)
                .userProperNum(userProperNum)
                .build();
    }
}
