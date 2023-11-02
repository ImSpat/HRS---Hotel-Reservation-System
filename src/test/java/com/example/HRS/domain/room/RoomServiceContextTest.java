package com.example.HRS.domain.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RoomServiceContextTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    @Sql("/create_test_data.sql")
    public void testGetSize() {
        //when
        RoomService roomService = new RoomService(roomRepository, null);
        List<Room> result = roomService.getRoomsForSize(2);

        //then
        assertEquals(2, result.size());
    }

    @Test
    @Sql("/create_test_data.sql")
    public void testGetSize2() {
        //when
        RoomService roomService = new RoomService(roomRepository, null);
        List<Room> result = roomService.getRoomsForSize(1);

        //then
        assertEquals(3, result.size());
    }
}
