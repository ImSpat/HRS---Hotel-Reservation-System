package com.example.HRS.domain.guest;

import com.example.HRS.controllers.dto.GuestCreationDTO;
import com.example.HRS.controllers.dto.GuestUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Guest newGuest = new Guest(dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth(), dto.getGender());
        this.repository.save(newGuest);
    }

    public void removeById(long id) {
        this.repository.deleteById(id);
    }

    public Guest getById(long id) {
        return this.repository.getById(id);
    }

    public void update(GuestUpdateDTO updatedGuest) {
        Guest guestById = this.repository.getById(updatedGuest.getId());
        guestById.update(
                updatedGuest.getFirstName(),
                updatedGuest.getLastName(),
                updatedGuest.getDateOfBirth(),
                updatedGuest.getGender()
        );
        this.repository.save(guestById);
    }
}