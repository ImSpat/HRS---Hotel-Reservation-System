package com.example.HRS.domain.room;

public enum BedType {
    SINGLE(1), DOUBLE(2);

    private final int size;

    BedType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
