package com.example.HRS.controllers;

import com.example.HRS.domain.guest.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/guests")
    public String guests(Model model) {
        model.addAttribute("guests", this.guestService.findAll());
        return "guests";
    }

    @GetMapping("/createNewGuest")
    public String createNewGuest() {
        return "createNewGuest";
    }

    @PostMapping("/createNewGuest")
    public String handleCreateNewGuest(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String dateOfBirth,
            @RequestParam String gender) {

        this.guestService.createNewGuest(firstName, lastName, dateOfBirth, gender);

        return "redirect:guests";

    }
}
