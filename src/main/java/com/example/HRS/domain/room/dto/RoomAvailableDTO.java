package com.example.HRS.domain.room.dto;

import com.example.HRS.domain.room.BedType;
import com.example.HRS.domain.room.Room;

import java.util.List;

public class RoomAvailableDTO {

    private final long id;
    private final String number;
    private final List<BedType> beds;
    private final int size;
    private final String description;
    private final List<String> photosUrls;

    public RoomAvailableDTO(long id, String number, List<BedType> beds, int size, String description, List<String> photosUrls) {
        this.id = id;
        this.number = number;
        this.beds = beds;
        this.size = size;
        this.description = description;
        this.photosUrls = photosUrls;
    }

    public RoomAvailableDTO(Room room) {
        this.id = room.getId();
        this.number = room.getNumber();
        this.beds = room.getBeds();
        this.size = room.getSize();
        this.description = room.getDescription();
        this.photosUrls = room.getPhotosUrls();
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

    public String getDescription() {
        return description;
    }

    public List<String> getPhotosUrls() {
        return photosUrls;
    }
}
