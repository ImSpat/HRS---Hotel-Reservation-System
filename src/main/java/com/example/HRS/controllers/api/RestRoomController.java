package com.example.HRS.controllers.api;

import com.example.HRS.domain.reservation.ReservationService;
import com.example.HRS.domain.room.Room;
import com.example.HRS.domain.room.RoomService;
import com.example.HRS.domain.room.dto.RoomAvailableDTO;
import com.example.HRS.domain.room.dto.RoomCreateRestDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestRoomController {

    private final ReservationService reservationService;
    private final RoomService roomService;

    @Autowired
    public RestRoomController(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping("api/getFreeRooms")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_RECEPTION')")
    public List<RoomAvailableDTO> getAvailableRooms(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            int size
    ) {
        try {
            List<Room> result = reservationService.getAvailableRooms(from, to, size);
            return result.stream().map(RoomAvailableDTO::new).collect(Collectors.toList());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }

    }

    @GetMapping("api/rooms")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_RECEPTION')")
    public ResponseEntity<List<RoomAvailableDTO>> getAllRooms() {
        List<RoomAvailableDTO> rooms = this.roomService.findAll().stream().map(RoomAvailableDTO::new).toList();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping("api/rooms")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<Room> createRoom(@RequestBody RoomCreateRestDTO dto) {
        Room newRoom = this.roomService.createNewRoom(dto.roomNumber(), dto.beds(), dto.description(), dto.photosUrls());
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @ApiResponse(description = "OK", responseCode = "200")
    @ApiResponse(description = "Forbidden, this room is reserved", responseCode = "403")
    @DeleteMapping("api/rooms/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> deleteRoom(@PathVariable long id) {
        try {
            this.roomService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }

    @PutMapping("api/rooms/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void updateRoom(@PathVariable long id, @RequestBody RoomCreateRestDTO dto) {
        this.roomService.update(id, dto.roomNumber(), dto.beds(), dto.description(), dto.photosUrls());
    }

    @PatchMapping("api/rooms/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void updateRoomByPatch(@PathVariable long id, @RequestBody RoomCreateRestDTO dto) {
        this.roomService.updateByPatch(id, dto.roomNumber(), dto.beds(), dto.description(), dto.photosUrls());
    }
}
