package tech.sanroque.magalums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.sanroque.magalums.service.NotificationService;

@Configuration
public class ServiceConfiguration {

    @Bean
    public NotificationService notificationService(){
        return new NotificationService();
    }
}
