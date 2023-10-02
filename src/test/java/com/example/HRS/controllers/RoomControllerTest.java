package com.example.HRS.controllers;

import com.example.HRS.domain.room.BedType;
import com.example.HRS.domain.room.Room;
import com.example.HRS.domain.room.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void basicRoomsTest() throws Exception {

        Room room = new Room("102", Arrays.asList(BedType.DOUBLE));

        Mockito.when(roomService.findAll()).thenReturn(Arrays.asList(room));

        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rooms"))
                .andExpect(view().name("rooms"))
                .andExpect(content().string(containsString("102")));
    }

    @Test
    public void handlePost() throws Exception {
        String postContent = "number=139&bedsDesc=2%2B1";

        MockHttpServletRequestBuilder request =
                post("/createNewRoom")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("rooms"));

        Mockito.verify(roomService, Mockito.times(1)).createNewRoom("139", "2+1");
    }
}
