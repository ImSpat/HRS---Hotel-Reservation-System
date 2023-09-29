package com.example.HRS.domain.guest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Guest {

    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final Gender gender;
}
