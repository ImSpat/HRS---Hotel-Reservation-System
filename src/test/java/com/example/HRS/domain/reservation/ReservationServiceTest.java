package com.example.HRS.domain.reservation;

import com.example.HRS.domain.room.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationServiceTest {

    @Test
    public void testIfGetAvRoomFailsForTooSmallSize() {

        //given
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        RoomService roomService = Mockito.mock(RoomService.class);
        ReservationService reservationService = new ReservationService(reservationRepository, roomService);

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
        ReservationService reservationService = new ReservationService(reservationRepository, roomService);

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
        ReservationService reservationService = new ReservationService(reservationRepository, roomService);

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
        ReservationService reservationService = new ReservationService(reservationRepository, roomService);

        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.now().plusDays(1),
                            LocalDate.now(), 15);
                }
        );
    }
}
