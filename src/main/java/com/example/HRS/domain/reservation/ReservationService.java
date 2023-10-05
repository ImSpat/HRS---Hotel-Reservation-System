package com.example.HRS.domain.reservation;

import com.example.HRS.domain.room.Room;
import com.example.HRS.domain.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository repository;
    private RoomService roomService;

    @Autowired
    public ReservationService(ReservationRepository repository, RoomService roomService) {
        this.repository = repository;
        this.roomService = roomService;
    }

    public List<Reservation> getAll() {
        return this.repository.findAll();
    }

    public List<Room> getAvailableRooms(LocalDate fromDate, LocalDate toDate, int size) {

        if (size < 0 || size > 10) {
            throw new IllegalArgumentException("Wrong size param [1-10]");
        }

        if (fromDate.isEqual(toDate) || toDate.isBefore(fromDate)) {
            throw new IllegalArgumentException("Wrong dates");
        }

        return this.roomService.findAll();
    }
}
