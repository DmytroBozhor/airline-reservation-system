package com.dmytrobozhor.airlinereservationservice.integration;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.FlightDetailSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractFlightDetailService;
import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import com.dmytrobozhor.airlinereservationservice.util.mappers.FlightDetailMapper;
import com.dmytrobozhor.airlinereservationservice.web.controller.FlightDetailController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@DisplayName("FlightDetail Controller IT")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class FlightDetailControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private FlightDetail flightDetail;

    private FlightDetailDto flightDetailDto;

    @BeforeEach
    void setUp() {

        var sourceAirportDto = new AirportDto(1, "Wong", "Bang", "Norway");

        var destinationAirportDto = new AirportDto(2, "Chong", "Wag", "Norway");

        var sourceAirport = Airport.builder()
                .id(sourceAirportDto.id())
                .name(sourceAirportDto.name())
                .city(sourceAirportDto.city())
                .country(sourceAirportDto.country())
                .build();

        var desinationAirport = Airport.builder()
                .id(destinationAirportDto.id())
                .name(destinationAirportDto.name())
                .city(destinationAirportDto.city())
                .country(destinationAirportDto.country())
                .build();

        flightDetail = FlightDetail
                .builder()
                .id(1)
                .departureDateTime(Timestamp.valueOf("2024-7-25 16:30:00"))
                .arrivalDateTime(Timestamp.valueOf("2024-7-26 11:30:00"))
                .airplaneType(AirplaneType.AIRBUS_A380)
                .sourceAirport(sourceAirport)
                .destinationAirport(desinationAirport)
                .build();

        flightDetailDto = new FlightDetailDto(
                flightDetail.getId(),
                flightDetail.getDepartureDateTime(),
                flightDetail.getArrivalDateTime(),
                flightDetail.getAirplaneType().toString(),
                sourceAirportDto,
                destinationAirportDto
        );


    }

    @SneakyThrows
    @Test
    @DisplayName("get all flight details")
    void whenGetAllFlightDetails_thenReturnAllFlightDetails() {

        var flightDetails = Collections.singletonList(flightDetail);
        var flightDetailDtos = Collections.singletonList(flightDetailDto);

        doReturn(flightDetails).when(flightDetailService).findAll();
        doReturn(flightDetailDtos).when(flightDetailMapper).toFlightDetailDto(flightDetails);

        var request = MockMvcRequestBuilders.get("/flight-details");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(flightDetailDtos.size())));

        verify(flightDetailService).findAll();
        verify(flightDetailMapper).toFlightDetailDto(anyList());

    }

    @SneakyThrows
    @Test
    @DisplayName("save flight detail")
    void whenSaveFlightDetail_thenReturnSavedFlightDetail() {

        var sourceAirportDto = new AirportDto(1, "Wong", "Bang", "Norway");
        var destinationAirportDto = new AirportDto(2, "Chong", "Wag", "Norway");

        var flightDetailSaveDto = new FlightDetailSaveDto(
                flightDetail.getDepartureDateTime(),
                flightDetail.getArrivalDateTime(),
                flightDetail.getAirplaneType().toString(), sourceAirportDto, destinationAirportDto
        );

        var flightDetailForSave = FlightDetail
                .builder()
                .departureDateTime(flightDetailSaveDto.departureDateTime())
                .arrivalDateTime(flightDetailSaveDto.arrivalDateTime())
                .airplaneType(AirplaneType.valueOf(flightDetailSaveDto.airplaneType()))
                .build();

        doReturn(flightDetailForSave).when(flightDetailMapper).toFlightDetail(flightDetailSaveDto);
        doReturn(flightDetail).when(flightDetailService).save(flightDetailForSave);
        doReturn(flightDetailDto).when(flightDetailMapper).toFlightDetailDto(flightDetail);

        var request = MockMvcRequestBuilders
                .post("/flight-details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flightDetailSaveDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(flightDetailDto.id())))
                .andExpect(jsonPath("$.departureDateTime", is("2024-07-25T14:30:00.000+00:00")))
                .andExpect(jsonPath("$.arrivalDateTime", is("2024-07-26T09:30:00.000+00:00")))
                .andExpect(jsonPath("$.airplaneType", is(flightDetailSaveDto.airplaneType())));

        verify(flightDetailService).save(any(FlightDetail.class));
        verify(flightDetailMapper).toFlightDetail(any(FlightDetailSaveDto.class));
        verify(flightDetailMapper).toFlightDetailDto(any(FlightDetail.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("get flight detail by id")
    void whenGetFlightDetailById_thenReturnFlightDetail() {

        doReturn(flightDetail).when(flightDetailService).findById(anyInt());
        doReturn(flightDetailDto).when(flightDetailMapper).toFlightDetailDto(flightDetail);

        var request = MockMvcRequestBuilders.get("/flight-details/{id}", flightDetail.getId());

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(jsonPath("$.id", is(flightDetailDto.id())))
                .andExpect(jsonPath("$.departureDateTime", is("2024-07-25T14:30:00.000+00:00")))
                .andExpect(jsonPath("$.arrivalDateTime", is("2024-07-26T09:30:00.000+00:00")))
                .andExpect(jsonPath("$.airplaneType", is(flightDetailDto.airplaneType())));

        verify(flightDetailService).findById(anyInt());
        verify(flightDetailMapper).toFlightDetailDto(any(FlightDetail.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("delete flight detail by id")
    void whenDeleteFlightDetailById_thenReturnNothing() {

        doNothing().when(flightDetailService).deleteById(anyInt());

        var request = MockMvcRequestBuilders.delete("/flight-details/{id}", flightDetail.getId());

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(flightDetailService).deleteById(anyInt());

    }

    @SneakyThrows
    @Test
    @DisplayName("update flight detail by id")
    void whenUpdateFlightDetail_thenReturnUpdatedFlightDetail() {

        var flightDetailPartialUpdateDto = new FlightDetailPartialUpdateDto(null, null,
                AirplaneType.BOEING_747.toString(), null, null);

        var flightDetailPartialUpdate = FlightDetail.builder()
                .airplaneType(AirplaneType.valueOf(flightDetailPartialUpdateDto.airplaneType())).build();

        var updatedFlightDetail = FlightDetail.builder()
                .id(flightDetail.getId())
                .airplaneType(flightDetailPartialUpdate.getAirplaneType())
                .departureDateTime(flightDetail.getDepartureDateTime())
                .arrivalDateTime(flightDetail.getArrivalDateTime())
                .build();

        var updatedFlightDetailDto = new FlightDetailDto(
                updatedFlightDetail.getId(),
                updatedFlightDetail.getDepartureDateTime(),
                updatedFlightDetail.getArrivalDateTime(),
                updatedFlightDetail.getAirplaneType().toString(),
                null, null
        );

        doReturn(flightDetailPartialUpdate).when(flightDetailMapper).toFlightDetail(eq(flightDetailPartialUpdateDto));
        doReturn(updatedFlightDetail).when(flightDetailService).updateById(anyInt(), eq(flightDetailPartialUpdate));
        doReturn(updatedFlightDetailDto).when(flightDetailMapper).toFlightDetailDto(eq(updatedFlightDetail));

        var request = MockMvcRequestBuilders
                .patch("/flight-details/{id}", flightDetail.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flightDetailPartialUpdateDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(flightDetail.getId())))
                .andExpect(jsonPath("$.airplaneType", is(flightDetailPartialUpdateDto.airplaneType())))
                .andExpect(jsonPath("$.departureDateTime", is("2024-07-25T14:30:00.000+00:00")))
                .andExpect(jsonPath("$.arrivalDateTime", is("2024-07-26T09:30:00.000+00:00")));

        verify(flightDetailService).updateById(anyInt(), any(FlightDetail.class));
        verify(flightDetailMapper).toFlightDetail(any(FlightDetailPartialUpdateDto.class));
        verify(flightDetailMapper).toFlightDetailDto(any(FlightDetail.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("update or create flight detail")
    void whenUpdateOrCreateFlightDetail_thenUpdateFlightDetail() {

        var updatedFlightDetail = FlightDetail.builder()
                .id(flightDetail.getId())
                .departureDateTime(flightDetail.getDepartureDateTime())
                .arrivalDateTime(flightDetail.getArrivalDateTime())
                .airplaneType(AirplaneType.BOEING_747)
                .sourceAirport(flightDetail.getSourceAirport())
                .destinationAirport(flightDetail.getDestinationAirport())
                .build();

        var updatedFlightDetailDto = new FlightDetailDto(
                updatedFlightDetail.getId(),
                updatedFlightDetail.getDepartureDateTime(),
                updatedFlightDetail.getArrivalDateTime(),
                updatedFlightDetail.getAirplaneType().toString(),
                flightDetailDto.sourceAirport(),
                flightDetailDto.destinationAirport()
        );

        doReturn(updatedFlightDetail).when(flightDetailMapper).toFlightDetail(any(FlightDetailSaveDto.class));
        doReturn(updatedFlightDetail).when(flightDetailService).updateOrCreateById(anyInt(), eq(updatedFlightDetail));
        doReturn(updatedFlightDetailDto).when(flightDetailMapper).toFlightDetailDto(updatedFlightDetail);

        var request = MockMvcRequestBuilders
                .put("/flight-details/{id}", flightDetail.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedFlightDetail));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedFlightDetail.getId())))
                .andExpect(jsonPath("$.departureDateTime", is("2024-07-25T14:30:00.000+00:00")))
                .andExpect(jsonPath("$.arrivalDateTime", is("2024-07-26T09:30:00.000+00:00")))
                .andExpect(jsonPath("$.airplaneType", is(updatedFlightDetail.getAirplaneType().toString())));

        verify(flightDetailService).updateOrCreateById(anyInt(), any(FlightDetail.class));
        verify(flightDetailMapper).toFlightDetail(any(FlightDetailSaveDto.class));
        verify(flightDetailMapper).toFlightDetailDto(any(FlightDetail.class));

    }

}