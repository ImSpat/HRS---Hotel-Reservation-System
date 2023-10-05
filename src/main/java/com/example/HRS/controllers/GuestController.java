package com.example.HRS.controllers;

import com.example.HRS.controllers.dto.GuestCreationDTO;
import com.example.HRS.controllers.dto.GuestUpdateDTO;
import com.example.HRS.domain.guest.Guest;
import com.example.HRS.domain.guest.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String guests(Model model) {
        model.addAttribute("guests", this.guestService.findAll());
        return "guests";
    }

    @GetMapping("/create")
    public String createNewGuest() {
        return "createNewGuest";
    }

    @PostMapping
    public String handleCreateNewGuest(@Valid GuestCreationDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "createNewGuest";
        }
        this.guestService.createNewGuest(dto);
        return "redirect:/guests";
    }

    @GetMapping("/delete/{id}")
    public String removeGuest(@PathVariable long id) {
        this.guestService.removeById(id);
        return "redirect:/guests";
    }

    @GetMapping("/edit/{id}")
    public String editGuest(@PathVariable long id, Model model) {
        Guest guest = this.guestService.getById(id);
        model.addAttribute("guest", guest);
        return "editGuest";
    }

    @PostMapping("/edit")
    public String editGuest(GuestUpdateDTO updatedGuest) {
        this.guestService.update(updatedGuest);
        return "redirect:/guests";
    }
}
