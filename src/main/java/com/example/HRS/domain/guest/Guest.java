package com.example.HRS.domain.guest;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter(value = AccessLevel.NONE)
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    @Column(name = "phone")
    String phoneNumber;

    public Guest() {
    }

    public Guest(String firstName, String lastName, LocalDate birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Guest(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = dateOfBirth;
    }

    public void update(String firstName, String lastName, LocalDate birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() > 20) {
            throw new IllegalArgumentException("Phone number too long, max 20 chars");
        }
        this.phoneNumber = phoneNumber;
    }

}
