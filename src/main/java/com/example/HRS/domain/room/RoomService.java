package com.example.HRS.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class RoomService {

    private RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> findAll() {
        return this.repository.findAll();
    }

    public Room createNewRoom(String roomNumber, String bedsDesc) {
        String[] splitedBedDesc = bedsDesc.split("\\+");
        List<BedType> beds = Arrays.stream(splitedBedDesc).map(stringToBedTypeMapping).toList();

        return this.repository.createNewRoom(roomNumber, beds);
    }

    private final Function<String, BedType> stringToBedTypeMapping = value -> {
        if ("1".equals(value)) {
            return BedType.SINGLE;
        } else if ("2".equals(value)) {
            return BedType.DOUBLE;
        } else {
            throw new IllegalArgumentException();
        }
    };
}
