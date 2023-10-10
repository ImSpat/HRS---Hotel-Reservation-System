package com.example.HRS.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> findAll() {
        return repository.findAll();
    }

    public Room createNewRoom(String roomNumber, String bedsDesc) {
        List<BedType> beds = getBedTypesList(bedsDesc);
        Room newRoom = new Room(roomNumber, beds);
        return this.repository.save(newRoom);
    }

    public void removeById(long id) {
        this.repository.deleteById(id);
    }

    public Room findById(long id) {
        return this.repository.getReferenceById(id);
    }

    public void update(long id, String number, String bedsDesc) {
        Room toUpdate = this.repository.getById(id);
        List<BedType> beds = getBedTypesList(bedsDesc);
        toUpdate.update(number, beds);
        this.repository.save(toUpdate);
    }

    private List<BedType> getBedTypesList(String bedsDesc) {
        String[] splitedBedDec = bedsDesc.split("\\+");
        return Arrays.stream(splitedBedDec)
                .map(stringToBedTypeMapping)
                .collect(Collectors.toList());
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

    public List<Room> getRoomsForSize(int size) {
        if (size <= 0) {
            return new ArrayList<>();
        }
        return this.repository.findAll()
                .stream()
                .filter(room -> room.getSize()>=size)
                .collect(Collectors.toList());
    }

    public Optional<Room> getRoomById(long roomId) {
        return this.repository.findById(roomId);
    }
}
