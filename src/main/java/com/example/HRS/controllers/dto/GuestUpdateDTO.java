package com.example.HRS.controllers.dto;

import com.example.HRS.domain.guest.Gender;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class GuestUpdateDTO {
    private final long id;
    private final String firstName;
    private final String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate dateOfBirth;
    private final Gender gender;
}
