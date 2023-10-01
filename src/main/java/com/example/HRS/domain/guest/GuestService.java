package com.example.HRS.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GuestService {

    private GuestRepository repository;

    @Autowired
    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> findAll() {
        return this.repository.findAll();
    }

    public void createNewGuest(String firstName, String lastName, String dateOfBirth, String gender) {

        LocalDate parsedDate = LocalDate.parse(dateOfBirth);
        Gender parsedGender = Gender.valueOf(gender);
        this.repository.createNewGuest(firstName, lastName, parsedDate, parsedGender);
    }
}
