package com.example.HRS.controllers;

import com.example.HRS.domain.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

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
    public String beginCreationWizard(){
        return "reservationStepOne";
    }
}
