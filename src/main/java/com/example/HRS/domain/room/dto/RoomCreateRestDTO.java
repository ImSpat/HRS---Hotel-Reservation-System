package com.example.HRS.domain.room.dto;

import com.example.HRS.domain.room.BedType;

import java.util.List;

public record RoomCreateRestDTO(
        String roomNumber,
        List<BedType> beds,
        String description,
        List<String> photosUrls) {
}
