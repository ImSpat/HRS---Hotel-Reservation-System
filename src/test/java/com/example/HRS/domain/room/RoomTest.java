package com.example.HRS.domain.room;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTest {

    @Test
    public void testRoomSizeZero() {
        Room room = new Room("102", new ArrayList<>());
        assertEquals(0, room.getSize());
    }

    @Test
    public void testRoomSizeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Room room = new Room("102", null);
        });
    }

    @Test
    public void testRoomSize() {
        Room room = new Room("102", Arrays.asList(BedType.SINGLE, BedType.DOUBLE));
        assertEquals(3, room.getSize());
    }

    @Test
    public void testBedsToString() {
        Room r = new Room("102", Arrays.asList(BedType.DOUBLE, BedType.SINGLE));
        String result = r.getBedsAsStr();
        assertEquals("2+1", result);
    }
}
