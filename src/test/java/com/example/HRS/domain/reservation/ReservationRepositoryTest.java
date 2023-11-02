package com.example.HRS.domain.reservation;

import com.example.HRS.domain.guest.Guest;
import com.example.HRS.domain.guest.GuestRepository;
import com.example.HRS.domain.room.BedType;
import com.example.HRS.domain.room.Room;
import com.example.HRS.domain.room.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void getConfirmed() {
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(1);
        LocalDate dob = LocalDate.now();

        Guest guest1 = new Guest("Jan", "Kowalski", dob);
        Guest guest2 = new Guest("Kasia", "Kowalski", dob);
        this.guestRepository.save(guest1);
        this.guestRepository.save(guest2);

        Room room = new Room("1", List.of(BedType.DOUBLE));
        this.roomRepository.save(room);

        Reservation reservation1 = new Reservation(from, to, guest1, room);
        reservation1.confirm();
        Reservation reservation2 = new Reservation(from, to, guest2, room);
        this.reservationRepository.save(reservation1);
        this.reservationRepository.save(reservation2);

        List<Reservation> result = this.reservationRepository.findByConfirmed(true);
        assertEquals("Jan", result.get(0).getOwner().getFirstName());

        List<Reservation> result2 = this.reservationRepository.findByConfirmed(false);
        assertEquals("Kasia", result2.get(0).getOwner().getFirstName());
    }
}
