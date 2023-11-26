package com.example.HRS.domain.reservation.events;

import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class TempReservationCreatedEvent extends ApplicationEvent {

    public final LocalDateTime creationDate;
    public final String email;
    public final long reservationId;

    public TempReservationCreatedEvent(Object context, String email, long reservationId) {
        super(context);
        this.creationDate = LocalDateTime.now();
        this.email = email;
        this.reservationId = reservationId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getEmail() {
        return email;
    }

    public long getReservationId() {
        return reservationId;
    }
}
