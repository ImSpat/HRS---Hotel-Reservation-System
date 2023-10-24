package com.example.HRS.domain.room.dto;

import java.util.List;

public record RoomCreateDTO(String number, String bedsDesc, String description, List<String> photosUrls) {}
