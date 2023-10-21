package com.example.HRS.controllers.api;

import com.example.HRS.controllers.dto.AvailableRoomDTO;
import com.example.HRS.domain.reservation.ReservationService;
import com.example.HRS.domain.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestRoomController {

    private final ReservationService reservationService;

    @Autowired
    public RestRoomController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("api/getFreeRooms")
    public List<AvailableRoomDTO> getAvailableRooms(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            int size
    ) {
        try {
            List<Room> result = reservationService.getAvailableRooms(from, to, size);
            return result.stream().map(AvailableRoomDTO::new).collect(Collectors.toList());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }

    }
}
