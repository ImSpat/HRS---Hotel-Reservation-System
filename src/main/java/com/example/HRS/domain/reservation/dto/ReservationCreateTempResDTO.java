package com.example.HRS.domain.reservation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ReservationCreateTempResDTO(@NotNull long roomId,
                                         @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                         @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
                                         @NotNull @Email String email) {}
