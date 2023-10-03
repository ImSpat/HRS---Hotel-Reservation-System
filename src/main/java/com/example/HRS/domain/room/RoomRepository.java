package com.example.HRS.domain.room;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {
    List<Room> rooms = new ArrayList<>();

    public RoomRepository() {
        Room room = new Room("102", Arrays.asList(BedType.SINGLE));
        Room r = new Room("203", Arrays.asList(BedType.DOUBLE));
        this.rooms.add(room);
        this.rooms.add(r);
    }

    public List<Room> findAll() {
        return this.rooms;
    }

    public Room createNewRoom(String roomNumber, List<BedType> beds) {
        Room r = new Room(roomNumber, beds);
        this.rooms.add(r);
        return r;
    }

    public void removeById(long id) {
        Room toBeDeleted = this.findById(id);
        this.rooms.remove(toBeDeleted);
    }

    public Room findById(long id) {
        return this.rooms.stream()
                .filter(room -> room.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
