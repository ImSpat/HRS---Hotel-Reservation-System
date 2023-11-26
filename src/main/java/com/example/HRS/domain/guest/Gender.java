package com.example.HRS.domain.guest;

public enum Gender {
    MALE("Mężczyzna"), FEMALE("Kobieta");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.gender;
    }
}
