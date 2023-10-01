package com.example.HRS.domain.room;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {

    public List<Room> findAll() {
        Room room1 = new Room("102", Arrays.asList(BedType.SINGLE));
        Room room2 = new Room("203", Arrays.asList(BedType.DOUBLE));

        return Arrays.asList(room1, room2);
    }
}
