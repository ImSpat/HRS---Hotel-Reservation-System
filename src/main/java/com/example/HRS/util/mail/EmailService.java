package com.example.HRS.util.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender sender;

    @Autowired
    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }
    public void sendConfirmationEmail(String email, long reservationId) {

        SimpleMailMessage mail = new SimpleMailMessage();

        String text = "http://localhost:8080/reservations/confirm"+reservationId;

        mail.setTo(email);
        mail.setFrom("systemhrsofficial@gmail.com");
        mail.setSubject("Potwierdź rezerwację");
        mail.setText("Dziękujemy za dokonanie rezerwacji, by ją potwierdzić kliknij w link: " + text);

        this.sender.send(mail);

    }
}
