package cl.idesoft.idesoftschallenge.controller;

import cl.idesoft.idesoftschallenge.model.Local;
import cl.idesoft.idesoftschallenge.service.LocalServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@WebMvcTest(LocalController.class)
class LocalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LocalServiceImpl localService;

    static List<Local> records;

    @BeforeAll
    public static void init(){
        records = new ArrayList<>(List.of(new Local("TEST", "TEST COMMUNE", "TEST 123", "999999")));
    }

    @Test
    void getLocalesJson() throws Exception {

        when(localService.getAllLocales()).thenReturn(records);

        mockMvc.perform(get("/api/locales")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getLocalesXml() throws Exception {

        when(localService.getAllLocales()).thenReturn(records);

        mockMvc.perform(get("/api/locales")
                        .accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"));
    }

    @Test
    void getLocalesByFilterJson() throws Exception {
        when(localService.getLocalesByCommune("TEST COMMUNE")).thenReturn(records);

        mockMvc.perform(get("/api/locales/TEST COMMUNE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getLocalesByFilterXml() throws Exception {
        when(localService.getLocalesByCommune("TEST COMMUNE")).thenReturn(records);

        mockMvc.perform(get("/api/locales/TEST COMMUNE")
                        .accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"));
    }
}