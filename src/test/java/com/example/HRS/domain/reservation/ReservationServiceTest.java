package com.example.HRS.domain.reservation;

import com.example.HRS.domain.room.Room;
import com.example.HRS.domain.room.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {

    @Test
    public void testIfGetAvRoomFailsForTooSmallSize() {

        //given
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        RoomService roomService = Mockito.mock(RoomService.class);
        ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
        ReservationService reservationService = new ReservationService(reservationRepository, roomService, publisher);

        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.of(2022, 10, 03),
                            LocalDate.of(2022, 10, 04), -5);
                }
        );
    }

    @Test
    public void testIfGetAvRoomFailsForTooBigSize() {

        //given
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        RoomService roomService = Mockito.mock(RoomService.class);
        ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
        ReservationService reservationService = new ReservationService(reservationRepository, roomService, publisher);

        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.now(),
                            LocalDate.now().plusDays(1), 15);
                }
        );
    }

    @Test
    public void testIfGetAvRoomFailsForSameDates() {

        //given
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        RoomService roomService = Mockito.mock(RoomService.class);
        ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
        ReservationService reservationService = new ReservationService(reservationRepository, roomService, publisher);

        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.now(),
                            LocalDate.now(), 5);
                }
        );
    }

    @Test
    public void testIfGetAvRoomFailsForToBeforeFrom() {

        //given
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        RoomService roomService = Mockito.mock(RoomService.class);
        ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
        ReservationService reservationService = new ReservationService(reservationRepository, roomService, publisher);

        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.now().plusDays(1),
                            LocalDate.now(), 15);
                }
        );
    }

    @Test
    public void testPredicateGetReservationForTheSameStartDatePositive() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate startDate = LocalDate.parse("2023-08-03");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationStartsAtTheSameDate(startDate))
                .toList();

        //then
        assertEquals(1,collected.size());
    }

    @Test
    public void testPredicateGetReservationForTheSameStartDateNegative() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate startDate = LocalDate.parse("2023-08-26");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationStartsAtTheSameDate(startDate))
                .toList();

        //then
        assertEquals(0,collected.size());

    }

    @Test
    public void testPredicateGetReservationForTheSameEndDatePositive() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate endDate = LocalDate.parse("2023-08-13");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationEndsAtTheSameDate(endDate))
                .toList();

        //then
        assertEquals(1,collected.size());
    }

    @Test
    public void testPredicateGetReservationForTheSameEndDateNegative() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate endDate = LocalDate.parse("2023-08-26");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationEndsAtTheSameDate(endDate))
                .toList();

        //then
        assertEquals(0,collected.size());

    }

    @Test
    public void testPredicateGetReservationForStartsBetweenPositive() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate startDate = LocalDate.parse("2023-07-12");
        LocalDate endDate = LocalDate.parse("2023-07-15");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationStartsBetween(startDate, endDate))
                .toList();

        //then
        assertEquals(1,collected.size());
    }

    @Test
    public void testPredicateGetReservationForStartsBetweenNegative() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate startDate = LocalDate.parse("2023-08-15");
        LocalDate endDate = LocalDate.parse("2023-08-25");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationStartsBetween(startDate, endDate))
                .toList();

        //then
        assertEquals(0,collected.size());
    }

    @Test
    public void testPredicateGetReservationForEndsBetweenPositive() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate startDate = LocalDate.parse("2023-07-14");
        LocalDate endDate = LocalDate.parse("2023-07-16");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationEndsBetween(startDate, endDate))
                .toList();

        //then
        assertEquals(1,collected.size());
    }

    @Test
    public void testPredicateGetReservationForEndsBetweenNegative() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate startDate = LocalDate.parse("2023-08-15");
        LocalDate endDate = LocalDate.parse("2023-08-25");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationEndsBetween(startDate, endDate))
                .toList();

        //then
        assertEquals(0,collected.size());
    }

    @Test
    public void testPredicateGetReservationContainsPositive() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate startDate = LocalDate.parse("2023-08-04");
        LocalDate endDate = LocalDate.parse("2023-08-12");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationContains(startDate, endDate))
                .toList();

        //then
        assertEquals(1,collected.size());
    }

    @Test
    public void testPredicateGetReservationContainsNegative() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, new Room("001", new ArrayList<>())));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, new Room("001", new ArrayList<>())));

        LocalDate startDate = LocalDate.parse("2023-08-15");
        LocalDate endDate = LocalDate.parse("2023-08-25");

        //when
        List<Reservation> collected = reservations
                .stream()
                .filter(ReservationService.reservationContains(startDate, endDate))
                .toList();

        //then
        assertEquals(0,collected.size());
    }

    @Test
    public void testIfRoomAvailablePositive() {
        //given
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        RoomService roomService = Mockito.mock(RoomService.class);
        ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
        ReservationService reservationService = new ReservationService(reservationRepository, roomService, publisher);

        List<Reservation> reservations = new ArrayList<>();

        Room room = new Room("101", new ArrayList<>());
        room.setId(101);

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, room));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, room));

        LocalDate startDate = LocalDate.parse("2023-08-15");
        LocalDate endDate = LocalDate.parse("2023-08-25");

        Mockito.when(reservationRepository.findAll()).thenReturn(reservations);

        //when
        boolean result = reservationService.checkIfRoomAvailableForDates(room, startDate, endDate);

        //then
        assertTrue(result);
    }

    @Test
    public void testIfRoomAvailableNegative() {
        //given
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        RoomService roomService = Mockito.mock(RoomService.class);
        ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
        ReservationService reservationService = new ReservationService(reservationRepository, roomService, publisher);

        List<Reservation> reservations = new ArrayList<>();

        Room room = new Room("101", new ArrayList<>());
        room.setId(101);

        LocalDate fromOne = LocalDate.parse("2023-07-13");
        LocalDate toOne = LocalDate.parse("2023-07-15");
        reservations.add(new Reservation(fromOne, toOne, null, room));

        LocalDate fromTwo = LocalDate.parse("2023-08-03");
        LocalDate toTwo = LocalDate.parse("2023-08-13");
        reservations.add(new Reservation(fromTwo, toTwo, null, room));

        LocalDate startDate = LocalDate.parse("2023-07-13");
        LocalDate endDate = LocalDate.parse("2023-08-25");

        Mockito.when(reservationRepository.findAll()).thenReturn(reservations);

        //when
        boolean result = reservationService.checkIfRoomAvailableForDates(room, startDate, endDate);

        //then
        assertFalse(result);
    }
}
