package com.example.socialmediafeed.domain.user.service;

import com.example.socialmediafeed.domain.user.dto.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private static final String SUBJECT = "Social Media Feed MOA! 회원가입 인증 메일입니다.";

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Transactional
    public void sendAsyncMail(String email, String randomNumber) {
        CompletableFuture.runAsync(() ->
                send(
                        EmailMessage.builder()
                                .to(email)
                                .subject(SUBJECT)
                                .message(createTemplateMessage(email, randomNumber))
                                .build()), getAsyncExecutor());
    }

    private void send(EmailMessage emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            messageHelper.setTo(emailMessage.getTo());
            messageHelper.setSubject(emailMessage.getSubject());
            messageHelper.setText(emailMessage.getMessage(), true);
            javaMailSender.send(mimeMessage);
            log.info("email send success to:: {}", emailMessage.getTo());
        } catch (MessagingException e) {
            log.info(e.toString());
        }
    }

    private Executor getAsyncExecutor() {
        return Executors.newCachedThreadPool();
    }

    private String createTemplateMessage(String email, String randomNumber) {
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("randomNumber", randomNumber);
        return templateEngine.process("mail/email", context);
    }

}
