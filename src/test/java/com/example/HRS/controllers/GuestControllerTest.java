package com.example.HRS.controllers;

import com.example.HRS.controllers.dto.GuestCreationDTO;
import com.example.HRS.domain.guest.Gender;
import com.example.HRS.domain.guest.Guest;
import com.example.HRS.domain.guest.GuestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void handlePost() throws Exception {

        String postContent = "firstName=Bart%C5%82omiej&lastName=Szczud%C5%82o&dateOfBirth=2023-04-15&gender=MALE";
        MockHttpServletRequestBuilder request =
                post("/createNewGuest")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent);
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("guests"));

        GuestCreationDTO dto = new GuestCreationDTO(
                "Bartłomiej",
                "Szczudło",
                LocalDate.parse("2023-04-15"),
                Gender.MALE
        );

        Mockito.verify(guestService, Mockito.times(1)).createNewGuest(dto);
    }
}
