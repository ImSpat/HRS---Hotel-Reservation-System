package com.example.HRS.controllers;

import com.example.HRS.domain.reservation.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void handleReservationCreationStepTwoValidPost() throws Exception {

        String postContent = "fromDate=2022-03-12&toDate=2022-03-13&size=2";
        LocalDate fromDate = LocalDate.parse("2022-03-12");
        LocalDate toDate = LocalDate.parse("2022-03-13");
        int size = 2;

        MockHttpServletRequestBuilder request =
                post("/reservations/create/steptwo")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent);

        mockMvc.perform(request)
                .andExpect(view().name("reservationStepTwo"))
                .andExpect(model().attribute("fromDate", fromDate))
                .andExpect(model().attribute("toDate", toDate));

        Mockito.verify(reservationService, Mockito.times(1))
                .getAvailableRooms(fromDate, toDate, size);
    }

    @Test
    public void handleReservationCreationStepTwoInvalidSizePost() throws Exception {

        String postContent = "fromDate=2022-03-12&toDate=2022-03-13&size=12";
        LocalDate fromDate = LocalDate.parse("2022-03-12");
        LocalDate toDate = LocalDate.parse("2022-03-13");
        int size = 12;

        MockHttpServletRequestBuilder request =
                post("/reservations/create/steptwo")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent);

        mockMvc.perform(request)
                .andExpect(view().name("reservationStepOne"))
                .andExpect(model().attributeExists("errors"));

        Mockito.verify(reservationService, Mockito.times(0))
                .getAvailableRooms(fromDate, toDate, size);
    }

    @Test
    public void handleReservationCreationStepTwoInvalidDatesPost() throws Exception {

        String postContent = "fromDate=2022-03-12&toDate=2022-03-12&size=2";
        LocalDate fromDate = LocalDate.parse("2022-03-12");
        LocalDate toDate = LocalDate.parse("2022-03-12");
        int size = 2;

        MockHttpServletRequestBuilder request =
                post("/reservations/create/steptwo")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent);

        mockMvc.perform(request)
                .andExpect(view().name("reservationStepOne"))
                .andExpect(model().attributeExists("errors"));

        Mockito.verify(reservationService, Mockito.times(0))
                .getAvailableRooms(fromDate, toDate, size);
    }

}
