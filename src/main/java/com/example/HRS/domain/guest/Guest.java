package com.example.HRS.domain.guest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Setter(value = AccessLevel.NONE)
public class Guest {

    private final long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;

    public Guest(String firstName, String lastName, LocalDate birthDate, Gender gender) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public void update(String firstName, String lastName, LocalDate birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
