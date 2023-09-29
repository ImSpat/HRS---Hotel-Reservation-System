package com.example.HRS.domain.reservation;

import com.example.HRS.domain.guest.Guest;
import com.example.HRS.domain.room.Room;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservation {

    private LocalDate fromDate;
    private LocalDate toDate;
    private Guest guest;
    private Room room;
}
