package com.example.HRS.domain.room;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {

    @Test
    public void createNewRoomValidData() {
        //given
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        RoomService roomService = new RoomService(roomRepository);
        ArgumentCaptor<Room> roomCaptor = ArgumentCaptor.forClass(Room.class);
        List<BedType> bedTypes = Arrays.asList(BedType.DOUBLE, BedType.SINGLE, BedType.SINGLE);
        Room room = new Room("102", bedTypes);

        //when
        roomService.createNewRoom("102", "2+1+1");

        //then
        Mockito.verify(roomRepository).save(roomCaptor.capture());
        assertEquals("102", roomCaptor.getValue().getNumber());
        assertEquals(3, roomCaptor.getValue().getBeds().size());
        assertEquals(BedType.DOUBLE, roomCaptor.getValue().getBeds().get(0));
        assertEquals(BedType.SINGLE, roomCaptor.getValue().getBeds().get(1));
        assertEquals(BedType.SINGLE, roomCaptor.getValue().getBeds().get(2));


    }

}
