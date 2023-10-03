package com.example.HRS.domain.room;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Room {
    private final long id;
    private String number;
    private List<BedType> beds;
    private int size;

    public Room(String number, List<BedType> beds) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        if (beds == null) {
            throw new IllegalArgumentException("Beds list can not be null");
        }
        this.number = number;
        List<BedType> bedsField = new ArrayList<>(beds);
        this.beds = bedsField;
        updateBeds();
    }

    public String getBedsAsStr() {
        String bedAsStr = this.beds.stream()
                .map(getBedTypeStringFunction())
                .collect(Collectors.joining("+"));
        return bedAsStr;
    }

    private Function<BedType, String> getBedTypeStringFunction() {
        return bedType -> {
            if (bedType == BedType.DOUBLE) {
                return "2";
            } else if (bedType == BedType.SINGLE) {
                return "1";
            } else {
                throw new IllegalStateException();
            }
        };
    }

    public void update(String number, List<BedType> beds) {
        this.number = number;
        this.beds = beds;
        updateBeds();
    }

    private void updateBeds() {
        this.size = this.beds.stream().mapToInt(BedType::getSize).sum();
    }
}
