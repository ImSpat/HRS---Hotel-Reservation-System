package com.example.HRS.domain.guest;

import com.example.HRS.controllers.dto.GuestCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GuestService {

    private GuestRepository repository;

    @Autowired
    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> findAll() {
        return this.repository.findAll();
    }

    public void createNewGuest(GuestCreationDTO dto) {
        this.repository.createNewGuest(dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth(), dto.getGender());
    }

    public void removeById(long id) {
        this.repository.removeById(id);
    }
}
