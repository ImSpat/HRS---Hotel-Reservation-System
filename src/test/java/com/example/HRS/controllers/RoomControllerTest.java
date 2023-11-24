package com.example.HRS.controllers;

import com.auth0.jwt.JWT;
import com.example.HRS.domain.room.BedType;
import com.example.HRS.domain.room.Room;
import com.example.HRS.domain.room.RoomService;
import com.example.HRS.security.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
@WithMockUser(username = "jankowalski", roles = {"MANAGER"} )
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
                post("/rooms/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent)
                        .with(csrf());
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));

        Mockito.verify(roomService, Mockito.times(1)).createNewRoom("139", "2+1", null, null);
    }

    @Test
    public void handleDelete() throws Exception {
        MockHttpServletRequestBuilder request =
                get("/rooms/delete/21");
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));
        Mockito.verify(roomService, Mockito.times(1)).removeById(21);
    }

    @Test
    public void handleShowEditForm() throws Exception {
        MockHttpServletRequestBuilder request =
                get("/rooms/edit/21");
        Room r = new Room("1408", Arrays.asList(BedType.DOUBLE, BedType.SINGLE));
        Mockito.when(roomService.findById(21)).thenReturn(r);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("room"))
                .andExpect(model().attribute("bedsAsStr", "2+1"))
                .andExpect(view().name("editRoom"));
        Mockito.verify(roomService, Mockito.times(1)).findById(21);
    }

    @Test
    public void handleUpdate() throws Exception {
        String postContent = "id=21&number=139&bedsDesc=2%2B1";
        MockHttpServletRequestBuilder request =
                post("/rooms/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent)
                        .with(csrf());
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));
        Mockito.verify(roomService, Mockito.times(1)).update(21, "139", "2+1", null, null);
    }
}
