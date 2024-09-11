package tech.sanroque.magalums.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.sanroque.magalums.dto.ScheduleNotificationDTO;
import tech.sanroque.magalums.email.EmailService;
import tech.sanroque.magalums.entity.Channel;
import tech.sanroque.magalums.entity.Notification;
import tech.sanroque.magalums.entity.Status;
import tech.sanroque.magalums.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    public Optional<Notification> findById(Long notificationId){
        return notificationRepository.findById(notificationId);
    }

    public void scheduleNotification(ScheduleNotificationDTO dto) {
        notificationRepository.save(dto.toNotification());
    }

    public void cancelNotification(Long notificationId){
        var notification = findById(notificationId);

        if(notification.isPresent()){
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification.get());
        }

    }

    public void checkAndSend(LocalDateTime dateTime){
       var notifications =  notificationRepository.findByStatusInAndDateTimeBefore(
               List.of(Status.Values.PENDING.toStatus(), Status.Values.ERROR.toStatus()),
               dateTime
       );

       notifications.forEach(sendNotification());
    }

    private Consumer<Notification> sendNotification() {
        return n -> {

            if (n.getChannel() == Channel.Values.EMAIL.toChannel()){
                emailService.sendEmailNotification(n.getDestination(), "Notification MicroService", n.getMessage());
                System.out.println("_____________________________________________ ");
            }


           n.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(n);
        };
    }
}
