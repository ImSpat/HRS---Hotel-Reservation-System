package com.example.HRS.domain.room;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
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

    @Test
    public void testGetRoomsForSize() {
        //given
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", Arrays.asList(BedType.SINGLE)));
        rooms.add(new Room("102", Arrays.asList(BedType.DOUBLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.SINGLE, BedType.DOUBLE)));
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        RoomService roomService = new RoomService(roomRepository);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        //when
        List<Room> result = roomService.getRoomsForSize(1);

        //then
        assertEquals(3, result.size());
    }

    @Test
    public void testGetNoRoomsForSize() {
        //given
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", Arrays.asList(BedType.SINGLE)));
        rooms.add(new Room("102", Arrays.asList(BedType.DOUBLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.SINGLE, BedType.DOUBLE)));
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        RoomService roomService = new RoomService(roomRepository);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        //when
        List<Room> result = roomService.getRoomsForSize(4);

        //then
        assertEquals(0, result.size());
    }

    @Test
    public void testGetRoomsForEqualSize() {
        //given
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", Arrays.asList(BedType.SINGLE)));
        rooms.add(new Room("102", Arrays.asList(BedType.DOUBLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.SINGLE, BedType.DOUBLE)));
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        RoomService roomService = new RoomService(roomRepository);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        //when
        List<Room> result = roomService.getRoomsForSize(3);

        //then
        assertEquals(1, result.size());
    }

    @Test
    public void testGetNoRoomsForWrongSize() {
        //given
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", Arrays.asList(BedType.SINGLE)));
        rooms.add(new Room("102", Arrays.asList(BedType.DOUBLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.SINGLE, BedType.DOUBLE)));
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        RoomService roomService = new RoomService(roomRepository);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        //when
        List<Room> result = roomService.getRoomsForSize(-1);

        //then
        assertEquals(0, result.size());
    }

}
