package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("FlightDetailControllerTest")
@WebMvcTest(controllers = FlightDetailController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class FlightDetailControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AbstractFlightDetailService flightDetailService;

    @MockBean
    private FlightDetailMapper flightDetailMapper;

    @Test
    @DisplayName("get all flight details")
    void whenGetAllFlightDetails_thenReturnAllFlightDetails() {


    }

    @SneakyThrows
    @Test
    @DisplayName("save flight detail")
    void whenSaveFlightDetail_thenReturnSavedFlightDetail() {

        var flightDetailSaveDto = new FlightDetailSaveDto(
                Timestamp.valueOf("2024-7-25 16:30:00"),
                Timestamp.valueOf("2024-7-26 11:30:00"),
                AirplaneType.AIRBUS_A380.toString(), null, null
        );

        var flightDetailForSave = FlightDetail
                .builder()
                .departureDateTime(flightDetailSaveDto.departureDateTime())
                .arrivalDateTime(flightDetailSaveDto.arrivalDateTime())
                .airplaneType(AirplaneType.valueOf(flightDetailSaveDto.airplaneType()))
                .build();

        var flightDetailSaved = FlightDetail
                .builder()
                .id(1)
                .departureDateTime(flightDetailSaveDto.departureDateTime())
                .arrivalDateTime(flightDetailSaveDto.arrivalDateTime())
                .airplaneType(AirplaneType.valueOf(flightDetailSaveDto.airplaneType()))
                .build();

        var flightDetailDto = new FlightDetailDto(1,
                Timestamp.valueOf("2024-7-25 16:30:00"),
                Timestamp.valueOf("2024-7-26 11:30:00"),
                AirplaneType.AIRBUS_A380.toString(), null, null
        );

        doReturn(flightDetailForSave).when(flightDetailMapper).toFlightDetail(flightDetailSaveDto);
        doReturn(flightDetailSaved).when(flightDetailService).save(flightDetailForSave);
        doReturn(flightDetailDto).when(flightDetailMapper).toFlightDetailDto(flightDetailSaved);

        var request = MockMvcRequestBuilders
                .post("/fright-details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flightDetailSaveDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(flightDetailDto.id())))
                .andExpect(jsonPath("$.departureDateTime", is(flightDetailSaveDto.departureDateTime())))
                .andExpect(jsonPath("$.arrivalDateTime", is(flightDetailSaveDto.arrivalDateTime())))
                .andExpect(jsonPath("$.airplaneType", is(flightDetailSaveDto.airplaneType())));

    }

    @Test
    @DisplayName("")
    void getFlightDetailById() {
    }

    @Test
    @DisplayName("")
    void deleteFlightDetailById() {
    }

    @Test
    @DisplayName("")
    void updateFlightDetail() {
    }

    @Test
    @DisplayName("")
    void updateOrCreateFlightDetail() {
    }

}