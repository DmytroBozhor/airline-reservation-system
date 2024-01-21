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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("FlightDetail Controller Tests")
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

    //    TODO: fix and refactor the test below
    @SneakyThrows
    @Test
    @DisplayName("get all flight details")
    void whenGetAllFlightDetails_thenReturnAllFlightDetails() {

        var flightDetail = FlightDetail
                .builder()
                .id(1)
                .departureDateTime(Timestamp.valueOf("2024-7-25 16:30:00"))
                .arrivalDateTime(Timestamp.valueOf("2024-7-26 11:30:00"))
                .airplaneType(AirplaneType.AIRBUS_A380)
                .build();

        var flightDetailDto = new FlightDetailDto(
                flightDetail.getId(),
                flightDetail.getDepartureDateTime(),
                flightDetail.getArrivalDateTime(),
                flightDetail.getAirplaneType().toString(), null, null
        );

        doReturn(Collections.singletonList(flightDetail)).when(flightDetailService).findAll();
        doReturn(Collections.singletonList(flightDetailDto)).when(flightDetailMapper).toFlightDetailDto(anyList());

        var request = MockMvcRequestBuilders.get("/fright-details");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(Collections.singletonList(flightDetailDto))));

        verify(flightDetailService).findAll();
        verify(flightDetailMapper).toFlightDetailDto(anyList());

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

        verify(flightDetailService).save(any(FlightDetail.class));
        verify(flightDetailMapper).toFlightDetail(any(FlightDetailSaveDto.class));
        verify(flightDetailMapper).toFlightDetailDto(any(FlightDetail.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("")
    void getFlightDetailById() {

        var flightDetail = FlightDetail
                .builder()
                .id(1)
                .departureDateTime(Timestamp.valueOf("2024-7-25 16:30:00"))
                .arrivalDateTime(Timestamp.valueOf("2024-7-26 11:30:00"))
                .airplaneType(AirplaneType.AIRBUS_A380)
                .build();

        var flightDetailDto = new FlightDetailDto(
                flightDetail.getId(),
                flightDetail.getDepartureDateTime(),
                flightDetail.getArrivalDateTime(),
                flightDetail.getAirplaneType().toString(), null, null
        );

        doReturn(flightDetail).when(flightDetailService).findById(anyInt());
        doReturn(flightDetailDto).when(flightDetailMapper).toFlightDetailDto(any(FlightDetail.class));

        var request = MockMvcRequestBuilders.get("/fright-details/1");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(jsonPath("$.id", is(flightDetailDto.id())))
                .andExpect(jsonPath("$.departureDateTime", is(flightDetailDto.departureDateTime())))
                .andExpect(jsonPath("$.arrivalDateTime", is(flightDetailDto.arrivalDateTime())))
                .andExpect(jsonPath("$.airplaneType", is(flightDetailDto.airplaneType())));

        verify(flightDetailService).findById(anyInt());
        verify(flightDetailMapper).toFlightDetailDto(any(FlightDetail.class));

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