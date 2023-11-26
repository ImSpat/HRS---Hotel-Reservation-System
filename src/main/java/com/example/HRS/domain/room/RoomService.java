package com.example.HRS.domain.room;

import com.example.HRS.domain.reservation.ReservationService;
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
    private final RoomRepository roomRepository;
    private final ReservationService reservationService;

    @Autowired
    public RoomService(RoomRepository roomRepository, ReservationService reservationService) {
        this.roomRepository = roomRepository;
        this.reservationService = reservationService;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room createNewRoom(String roomNumber, String bedsDesc, String description, List<String> photosUrls) {
        List<BedType> beds = getBedTypesList(bedsDesc);
        Room newRoom = new Room(roomNumber, beds, description, photosUrls);
        return this.roomRepository.save(newRoom);
    }

    public Room createNewRoom(String roomNumber, List<BedType> beds, String description, List<String> photosUrls) {
        Room newRoom = new Room(roomNumber, beds, description, photosUrls);
        return this.roomRepository.save(newRoom);
    }

    public void removeById(long id) {
        boolean roomIsReserved = this.reservationService
                .getAll().stream().anyMatch(room -> room.getRoom().getId() == id);
        if (roomIsReserved) {
            throw new IllegalStateException("This room is reserved");
        } else {
            this.roomRepository.deleteById(id);
        }
    }

    public Room findById(long id) {
        return this.roomRepository.getReferenceById(id);
    }

    public void update(long id, String number, String bedsDesc, String description, List<String> photosUrls) {
        Room toUpdate = this.roomRepository.getById(id);
        List<BedType> beds = getBedTypesList(bedsDesc);
        toUpdate.update(number, beds, description, photosUrls);
        this.roomRepository.save(toUpdate);
    }

    public void update(long id, String number, List<BedType> beds, String description, List<String> photosUrls) {
        Room toUpdate = this.roomRepository.getReferenceById(id);
        toUpdate.update(number, beds, description, photosUrls);
        this.roomRepository.save(toUpdate);
    }

    public void updateByPatch(long id, String roomNumber, List<BedType> beds, String description, List<String> photosUrls) {

        Room toUpdate = this.roomRepository.getReferenceById(id);

        String newNumber = toUpdate.getNumber();
        if (roomNumber != null) {
            newNumber = roomNumber;
        }
        List<BedType> newBeds = toUpdate.getBeds();
        if (beds != null) {
            newBeds = beds;
        }
        String newDescription = toUpdate.getDescription();
        if (description != null) {
            newDescription = description;
        }
        List<String> newPhotosUrls = toUpdate.getPhotosUrls();
        if (photosUrls != null) {
            newPhotosUrls = photosUrls;
        }
        toUpdate.update(newNumber, newBeds, newDescription, newPhotosUrls);
        this.roomRepository.save(toUpdate);
    }

    public List<Room> getRoomsForSize(int size) {
        if (size <= 0) {
            return new ArrayList<>();
        }
        return this.roomRepository.findBySizeGreaterThanEqual(size);
    }

    public Optional<Room> getRoomById(long roomId) {
        return this.roomRepository.findById(roomId);
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
