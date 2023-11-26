package com.example.HRS.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByConfirmed(Boolean confirmed);

    List<Reservation> findByRoom_Id(Long id);
}
