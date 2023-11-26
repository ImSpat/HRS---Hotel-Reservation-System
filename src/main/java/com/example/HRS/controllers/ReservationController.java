package com.example.HRS.controllers;

import com.example.HRS.domain.reservation.ReservationService;
import com.example.HRS.domain.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String reservations(Model model) {
        model.addAttribute("reservations", reservationService.getAll());
        return "reservations";
    }

    @GetMapping("/create/stepone")
    public String beginCreationWizard() {
        return "reservationStepOne";
    }

    @PostMapping("/create/steptwo")
    public String creationWizardStepTwo(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            int size,
            String email,
            Model model) {

        List<String> errors = new ArrayList<>();

        if (size < 0 || size > 10) {
            errors.add("Nieprawidłowa ilość osób. Pokoje mieszczą max 10 osób.");
        }

        if (fromDate.isEqual(toDate) || toDate.isBefore(fromDate)) {
            errors.add("Nieprawidłowe daty rezerwacji");
        }

        if (errors.isEmpty()) {
            List<Room> rooms = this.reservationService.getAvailableRooms(fromDate, toDate, size);
            model.addAttribute("rooms", rooms);
            model.addAttribute("fromDate", fromDate);
            model.addAttribute("toDate", toDate);
            model.addAttribute("email", email);
            return "reservationStepTwo";
        } else {
            model.addAttribute("errors", errors);
            return "reservationStepOne";
        }
    }

    @PostMapping("/create/stepthree")
    public String finalizeReservation(long roomId,
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
                                      String email) {
        this.reservationService.createTemporaryReservation(roomId, fromDate, toDate, email);
        return "reservationConfirmed";
    }

    @GetMapping("/confirm/{reservationId}")
    public String confirmReservation(@PathVariable long reservationId, Model model) {
        boolean success = this.reservationService.confirmReservation(reservationId);
        model.addAttribute("success", success);
        model.addAttribute("reservationId", reservationId);
        return "reservationConfirmation";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable long id) {
        this.reservationService.removeById(id);
        return "redirect:/reservations";
    }
}
