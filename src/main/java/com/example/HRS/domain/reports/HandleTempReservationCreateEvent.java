package com.example.HRS.domain.reports;

import com.example.HRS.domain.reservation.events.TempReservationCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HandleTempReservationCreateEvent implements ApplicationListener<TempReservationCreatedEvent> {
    @Override
    public void onApplicationEvent(TempReservationCreatedEvent event) {
        System.out.println("Handle event by implementation");
    }
}
