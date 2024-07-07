package com.creativedesignproject.kumoh_board_backend.Auth.service;

import java.util.function.Consumer;

import org.springframework.mail.MailMessage;

public interface MailClient {
    void sendMail(Consumer<MailMessage> mailMessageConsumer);
}
