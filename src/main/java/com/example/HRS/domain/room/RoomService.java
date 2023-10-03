package com.example.HRS.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
        return this.repository.createNewRoom(roomNumber, beds);
    }

    public void removeById(long id) {
        this.repository.removeById(id);
    }

    public Room findById(long id) {
        return this.repository.findById(id);
    }

    public void update(long id, String number, String bedsDesc) {
        Room toUpdate = this.repository.findById(id);
        List<BedType> beds = getBedTypesList(bedsDesc);
        toUpdate.update(number, beds);
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
}
