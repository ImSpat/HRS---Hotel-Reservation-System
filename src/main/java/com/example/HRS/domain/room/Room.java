package com.example.HRS.domain.room;

import lombok.Data;

import java.util.List;

@Data
public class Room {

    private final String number;
    private final List<BedType> beds;
    private final int size;

    public Room(String number, List<BedType> beds) {
        if (beds == null) {
            throw new IllegalArgumentException("Beds list cannot be null");
        }

        this.number = number;
        this.beds = beds;
        this.size = calculateSize(beds);
    }

    private int calculateSize(List<BedType> beds) {
        int totalSize = 0;
        for (BedType bed : beds) {
            totalSize += bed.getSize();
        }
        return totalSize;
    }

    @Override
    public String toString() {
        return this.number;
    }
}
