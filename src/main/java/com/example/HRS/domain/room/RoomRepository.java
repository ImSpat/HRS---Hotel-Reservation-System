package com.example.HRS.domain.room;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {

    public List<Room> findAll() {
        Room room1 = new Room("102");
        Room room2 = new Room("203");

        return Arrays.asList(room1, room2);
    }
}
