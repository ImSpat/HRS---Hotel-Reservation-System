package com.example.HRS.controllers;

import com.example.HRS.domain.guest.Gender;
import com.example.HRS.domain.guest.Guest;
import com.example.HRS.domain.guest.GuestService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Test
    public void basic() throws Exception {

        Guest guest = new Guest("Jan", "Kowalski", LocalDate.of(1986, 11, 13), Gender.MALE);

        Mockito.when(guestService.findAll()).thenReturn(Arrays.asList(guest));

        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(view().name("guests"))
                .andExpect(content().string(containsString("1986-11-13")));
    }
}
