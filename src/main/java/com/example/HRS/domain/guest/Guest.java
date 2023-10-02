package com.example.HRS.domain.guest;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Guest {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final Gender gender;

    public Guest(String firstName, String lastName, LocalDate birthDate, Gender gender) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
