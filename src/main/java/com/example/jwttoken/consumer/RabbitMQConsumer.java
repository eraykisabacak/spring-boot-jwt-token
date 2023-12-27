package com.example.jwttoken.consumer;

import com.example.jwttoken.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    private EmailService emailService;

    public RabbitMQConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void sendWelcomeEmail(String email) {
        log.info("Recieved Message From RabbitMQ: " + email);
        emailService.sendWelcomeEmail(email);
    }
}
