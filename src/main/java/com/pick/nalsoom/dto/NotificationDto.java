package com.pick.nalsoom.dto;

import com.pick.nalsoom.domain.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {

    private Long notificationProperNum;
    private String notificationContent;
    private String notificationType;
    private String notificationReadYn;
    private String userProperNum;

    public Notification toEntity() {
        return Notification.builder()
                .notificationProperNum(notificationProperNum)
                .notificationContent(notificationContent)
                .notificationType(notificationType)
                .notificationReadYn(notificationReadYn)
                .userProperNum(userProperNum)
                .build();
    }

}
