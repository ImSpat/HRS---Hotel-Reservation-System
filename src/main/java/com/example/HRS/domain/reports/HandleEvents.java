package com.example.HRS.domain.reports;

import com.example.HRS.domain.reservation.events.TempReservationCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HandleEvents {

    @Async
    @EventListener
    public void handleTempReservationCreatedEvent(TempReservationCreatedEvent event) {
        System.out.println("Handle event by annotations");

    }
}
