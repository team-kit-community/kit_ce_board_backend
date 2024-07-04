package com.creativedesignproject.kumoh_board_backend.Auth.service.serviceimpl;

import java.util.function.Consumer;

import org.springframework.mail.MailMessage;

public interface MailClient {
    void sendMail(Consumer<MailMessage> mailMessageConsumer);
}
