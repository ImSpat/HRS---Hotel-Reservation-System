package com.example.HRS.domain.guest;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GuestRepository {

    List<Guest> guests = new ArrayList<>();

    public GuestRepository() {
        Guest guest = new Guest("Jan", "Kowalski", LocalDate.of(1986, 11, 13), Gender.MALE);
        Guest guest1 = new Guest("Kasia", "Kowalska", LocalDate.of(1988, 10, 14), Gender.FEMALE);

        this.guests.addAll(Arrays.asList(guest, guest1));
    }

    public List<Guest> findAll() {
        return this.guests;
    }

    public void createNewGuest(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        Guest newOne = new Guest(firstName, lastName, dateOfBirth, gender);
        this.guests.add(newOne);
    }

    public void removeById(long id) {
        Guest guestToBeDeleted = this.guests.stream()
                .filter(guest -> guest.getId() == id)
                .findFirst()
                .orElseThrow();

        this.guests.remove(guestToBeDeleted);
    }
}
