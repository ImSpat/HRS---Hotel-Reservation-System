package com.example.HRS.util.mail;

import com.example.HRS.domain.reservation.events.TempReservationCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HandleMailTempReservationCreateEvent implements ApplicationListener<TempReservationCreatedEvent> {
    @Override
    public void onApplicationEvent(TempReservationCreatedEvent event) {
        System.out.println("Mail - Handle event by implementation");
    }
}
