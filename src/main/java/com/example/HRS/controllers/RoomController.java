package com.example.HRS.controllers;

import com.example.HRS.domain.room.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        Room room = new Room("102");
        model.addAttribute("room", room);
        return "rooms";
    }
}
