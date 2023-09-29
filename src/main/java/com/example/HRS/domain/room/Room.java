package com.example.HRS.domain.room;

import lombok.Data;

@Data
public class Room {

    private final String number;

    @Override
    public String toString() {
        return this.number;
    }
}
