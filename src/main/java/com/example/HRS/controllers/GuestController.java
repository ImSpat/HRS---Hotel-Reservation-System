package com.example.HRS.controllers;

import com.example.HRS.domain.guest.Gender;
import com.example.HRS.domain.guest.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class GuestController {

    @GetMapping("/guests")
    public String guests(Model model) {
        Guest guest = new Guest("Jan", "Kowalski", LocalDate.of(1986, 11, 13), Gender.MALE);
        model.addAttribute("guest", guest);
        return "guests";
    }
}
