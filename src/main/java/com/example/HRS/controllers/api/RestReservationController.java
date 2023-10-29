package com.example.HRS.controllers.api;

import com.example.HRS.domain.reservation.ReservationService;
import com.example.HRS.domain.reservation.dto.ReservationCreateTempResDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RestReservationController {

    private final ReservationService reservationService;

    @Autowired
    public RestReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("api/createTemporaryReservation")
    public ResponseEntity<?> createTempReservation(@Valid @RequestBody ReservationCreateTempResDTO payload) {
        boolean result = this.reservationService.createTemporaryReservation(payload.roomId(), payload.fromDate(), payload.toDate(), payload.email());

        if (!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to find room with specified ID");
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
