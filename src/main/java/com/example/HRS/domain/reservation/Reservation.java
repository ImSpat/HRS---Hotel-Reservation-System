package com.example.HRS.domain.reservation;

import com.example.HRS.domain.guest.Guest;
import com.example.HRS.domain.room.Room;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean confirmed;
    private String email;
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Guest owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    public Reservation() {
    }

    public Reservation(LocalDate fromDate, LocalDate toDate, Guest owner, Room room) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.owner = owner;
        this.room = room;
    }

    public Reservation(LocalDate fromDate, LocalDate toDate, boolean confirmed, String email, LocalDateTime creationDate, Guest owner, Room room) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.confirmed = confirmed;
        this.email = email;
        this.creationDate = creationDate;
        this.owner = owner;
        this.room = room;
    }

    public Reservation(LocalDate fromDate, LocalDate toDate, Room room, String email) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.room = room;
        this.email = email;
        this.confirmed = false;
        this.creationDate = LocalDateTime.now();
    }

    public void confirm() {
        this.confirmed = true;
    }
}
