package com.example.HRS.controllers.dto;

import com.example.HRS.domain.room.BedType;
import com.example.HRS.domain.room.Room;

import java.util.List;

public class AvailableRoomDTO {

    private final long id;
    private final String number;
    private final List<BedType> beds;
    private final int size;

    public AvailableRoomDTO(long id, String number, List<BedType> beds, int size) {
        this.id = id;
        this.number = number;
        this.beds = beds;
        this.size = size;
    }

    public AvailableRoomDTO(Room room) {
        this.id = room.getId();
        this.number = room.getNumber();
        this.beds = room.getBeds();
        this.size = room.getSize();
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public List<BedType> getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }
}
