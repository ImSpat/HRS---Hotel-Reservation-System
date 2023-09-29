package com.example.HRS.domain.guest;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public class GuestRepository {

    public List<Guest> findAll() {
        Guest guest = new Guest("Jan", "Kowalski", LocalDate.of(1986, 11, 13), Gender.MALE);
        Guest guest1 = new Guest("Kasia", "Kowalska", LocalDate.of(1988, 10, 14), Gender.FEMALE);

        return Arrays.asList(guest, guest1);
    }

}
