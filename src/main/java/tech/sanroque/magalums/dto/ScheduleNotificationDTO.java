package tech.sanroque.magalums.dto;

import org.springframework.beans.BeanUtils;
import tech.sanroque.magalums.entity.Channel;
import tech.sanroque.magalums.entity.Notification;
import tech.sanroque.magalums.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDTO(LocalDateTime dateTime,
                                      String destination,
                                      String message,
                                      Channel.Values channel) {


    public Notification toNotification() {
        return new Notification(
                dateTime,
                destination,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()
        );
    }
}
