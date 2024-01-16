package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractAirportService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Airport Controller Test")
@WebMvcTest(controllers = AirportController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith({
        MockitoExtension.class,
        SpringExtension.class
})
class AirportControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbstractAirportService airportService;

    @MockBean
    private AirportMapper airportMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    @DisplayName("POST API -> /airports")
    void whenSaveAirportDto_thenReturnSavedAirportDto() {

        var airportDto = new AirportDto(1, "Wong", "Bang", "Norway");
        var airport = Airport.builder().id(1).name("Wong").city("Bang").country("Norway").build();

        doReturn(airport).when(airportMapper).toAirport(any(AirportSaveDto.class));
        doReturn(airportDto).when(airportMapper).toAirportDto(any(Airport.class));
        doReturn(airport).when(airportService).save(any(Airport.class));

        var request = MockMvcRequestBuilders
                .post("/airports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(airportDto.id())))
                .andExpect(jsonPath("$.name", is(airportDto.name())))
                .andExpect(jsonPath("$.city", is(airportDto.city())))
                .andExpect(jsonPath("$.country", is(airportDto.country())));

//        verify(airportMapper).toAirport(any(AirportSaveDto.class));
//        verify(airportMapper).toAirportDto(any(Airport.class));
        verify(airportService).save(any(Airport.class));

    }
}