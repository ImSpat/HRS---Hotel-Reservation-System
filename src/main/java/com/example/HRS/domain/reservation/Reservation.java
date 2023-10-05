package com.example.HRS.domain.reservation;

import com.example.HRS.domain.guest.Guest;
import com.example.HRS.domain.room.Room;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate fromDate;
    private LocalDate toDate;
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
}
