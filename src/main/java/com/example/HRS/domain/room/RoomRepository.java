package com.example.HRS.domain.room;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {

    public List<Room> rooms = new ArrayList<>();

    public RoomRepository() {
        Room room1 = new Room("102", Arrays.asList(BedType.SINGLE));
        Room room2 = new Room("203", Arrays.asList(BedType.DOUBLE));
        this.rooms.addAll(Arrays.asList(room1, room2));
    }

    public List<Room> findAll() {
        return this.rooms;
    }

    public Room createNewRoom(String roomNumber, List<BedType> beds) {
        Room room = new Room(roomNumber, beds);
        this.rooms.add(room);
        return room;
    }
}
