package com.example.HRS.domain.reservation;

import com.example.HRS.domain.reservation.events.TempReservationCreatedEvent;
import com.example.HRS.domain.room.Room;
import com.example.HRS.domain.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationRepository repository;
    private RoomService roomService;
    private ApplicationEventPublisher publisher;

    @Autowired
    public ReservationService(ReservationRepository repository, RoomService roomService, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.roomService = roomService;
        this.publisher = publisher;
    }

    public List<Reservation> getAll() {
        return this.repository.findAll();
    }

    public List<Room> getAvailableRooms(LocalDate fromDate, LocalDate toDate, int size) {

        List<Room> availableRooms = new ArrayList<>();

        if (size < 0 || size > 10) {
            throw new IllegalArgumentException("Wrong size param [1-10]");
        }

        if (fromDate.isEqual(toDate) || toDate.isBefore(fromDate)) {
            throw new IllegalArgumentException("Wrong dates");
        }

        List<Room> roomsWithProperSize = this.roomService.getRoomsForSize(size);

        for (Room room : roomsWithProperSize) {
            if (checkIfRoomAvailableForDates(room, fromDate, toDate)) {
                availableRooms.add(room);
            }

        }

        return availableRooms;
    }

    public boolean checkIfRoomAvailableForDates(Room room, LocalDate fromDate, LocalDate toDate) {
        List<Reservation> reservations = getAllReservationsForRoom(room);
        Optional<Reservation> any = reservations.stream()
                .filter(
                        reservationStartsAtTheSameDate(fromDate)
                                .or(reservationEndsAtTheSameDate(toDate))
                                .or(reservationStartsBetween(fromDate, toDate))
                                .or(reservationEndsBetween(fromDate, toDate))
                                .or(reservationContains(fromDate, toDate))
                )
                .findAny();
        return any.isEmpty();
    }

    static Predicate<Reservation> reservationContains(LocalDate fromDate, LocalDate toDate) {
        return reservation -> reservation.getFromDate().isBefore(fromDate) && reservation.getToDate().isAfter(toDate);
    }

    static Predicate<Reservation> reservationEndsBetween(LocalDate fromDate, LocalDate toDate) {
        return reservation -> reservation.getToDate().isAfter(fromDate) && reservation.getToDate().isBefore(toDate);
    }

    static Predicate<Reservation> reservationStartsBetween(LocalDate fromDate, LocalDate toDate) {
        return reservation -> reservation.getFromDate().isAfter(fromDate) && reservation.getFromDate().isBefore(toDate);
    }

    static Predicate<Reservation> reservationEndsAtTheSameDate(LocalDate toDate) {
        return reservation -> reservation.getToDate().equals(toDate);
    }

    static Predicate<Reservation> reservationStartsAtTheSameDate(LocalDate fromDate) {
        return reservation -> reservation.getFromDate().equals(fromDate);
    }

    private List<Reservation> getAllReservationsForRoom(Room room) {
        return this.repository.findAll()
                .stream().filter(reservation -> reservation.getRoom().getId() == room.getId())
                .collect(Collectors.toList());
    }

    public boolean createTemporaryReservation(long roomId, LocalDate fromDate, LocalDate toDate, String email) {
        Optional<Room> room = this.roomService.getRoomById(roomId);
        room.ifPresent(r -> {
            Reservation temporaryReservation = new Reservation(fromDate, toDate, r, email);
            this.repository.save(temporaryReservation);
            TempReservationCreatedEvent event = new TempReservationCreatedEvent(this, email, r.getId());
            publisher.publishEvent(event);
            System.out.println("UDAŁO SIĘ UTWORZYĆ REZERWACJE");
        });
        return room.isPresent();
    }
}
