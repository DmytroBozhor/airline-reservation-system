package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractAirportService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Airport Controller Tests")
@WebMvcTest(controllers = AirportController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AirportControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbstractAirportService airportService;

    @MockBean
    private AirportMapper airportMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Airport airport;

    private AirportDto airportDto;

    @BeforeEach
    void setUp() {
        airport = Airport.builder().id(1).name("Wong").city("Bang").country("Norway").build();
        airportDto = new AirportDto(1, "Wong", "Bang", "Norway");
    }

    @SneakyThrows
    @Test
    @DisplayName("POST API -> /airports")
    void whenSaveAirportDto_thenReturnSavedAirportDto() {

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

        verify(airportMapper).toAirport(any(AirportSaveDto.class));
        verify(airportMapper).toAirportDto(any(Airport.class));
        verify(airportService).save(any(Airport.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("get airport by id")
    void whenGetById_returnFoundAirport() {

        doReturn(airport).when(airportService).findById(anyInt());
        doReturn(airportDto).when(airportMapper).toAirportDto(any(Airport.class));

        var request = MockMvcRequestBuilders
                .get("/airports/1");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(airportDto.id())))
                .andExpect(jsonPath("$.name", is(airportDto.name())))
                .andExpect(jsonPath("$.city", is(airportDto.city())))
                .andExpect(jsonPath("$.country", is(airportDto.country())));

        verify(airportService).findById(anyInt());
        verify(airportMapper).toAirportDto(any(Airport.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("get airport by not existing id")
    void whenGetById_returnThrowException() {

        doThrow(EntityNotFoundException.class).when(airportService).findById(anyInt());

        var request = MockMvcRequestBuilders.get("/airports/1");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(airportService).findById(anyInt());

    }

    @SneakyThrows
    @Test
    @DisplayName("delete airport by id")
    void whenDeleteById_thenReturnNothing() {

        doNothing().when(airportService).deleteById(anyInt());

        var request = MockMvcRequestBuilders.delete("/airports/1");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(airportService).deleteById(anyInt());

    }

    @SneakyThrows
    @Test
    @DisplayName("get all airports")
    void whenGetAll_thenReturnAllAirports() {

        var airport1 = Airport.builder().id(2).name("Chin").city("Mu").country("China").build();
        var airportDto1 = new AirportDto(2, "Chin", "Mu", "China");

        var airports = List.of(airport, airport1);
        var airportDtos = List.of(airportDto, airportDto1);

        doReturn(airports).when(airportService).findAll();
        doReturn(airportDtos).when(airportMapper).toAirportDto(anyList());

        var request = MockMvcRequestBuilders
                .get("/airports");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(airports.size())));

        verify(airportService).findAll();
        verify(airportMapper).toAirportDto(anyList());

    }

    @SneakyThrows
    @Test
    @DisplayName("update airport by id")
    void whenUpdateById_thenUpdateAirport() {

        var airportPartialUpdateDto = new AirportPartialUpdateDto(null, "Vatican", "Italy");
        var airportPartialUpdate = Airport.builder().city("Vatican").country("Italy").build();
        var updatedAirport = Airport.builder()
                .id(airport.getId())
                .name(airport.getName())
                .city(airportPartialUpdate.getCity())
                .country(airportPartialUpdate.getCountry())
                .build();
        var updatedAirportDto = new AirportDto(
                updatedAirport.getId(),
                updatedAirport.getName(),
                updatedAirport.getCity(),
                updatedAirport.getCountry()
        );

        doReturn(airportPartialUpdate).when(airportMapper).toAirport(airportPartialUpdateDto);
        doReturn(updatedAirport).when(airportService).updateById(eq(airport.getId()), eq(airportPartialUpdate));
        doReturn(updatedAirportDto).when(airportMapper).toAirportDto(updatedAirport);

        var request = MockMvcRequestBuilders
                .patch("/airports/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportPartialUpdateDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedAirport.getId())))
                .andExpect(jsonPath("$.name", is(updatedAirport.getName())))
                .andExpect(jsonPath("$.city", is(updatedAirport.getCity())))
                .andExpect(jsonPath("$.country", is(updatedAirport.getCountry())));

        verify(airportMapper).toAirport(any(AirportPartialUpdateDto.class));
        verify(airportService).updateById(anyInt(), any(Airport.class));
        verify(airportMapper).toAirportDto(any(Airport.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("update by not existing id")
    void whenUpdateById_thenThrowException() {

        var airportPartialUpdateDto = new AirportPartialUpdateDto(null, "Vatican", "Italy");
        var airportPartialUpdate = Airport.builder().city("Vatican").country("Italy").build();

        doReturn(airportPartialUpdate).when(airportMapper).toAirport(airportPartialUpdateDto);
        doThrow(EntityNotFoundException.class).when(airportService).updateById(anyInt(), eq(airportPartialUpdate));

        var request = MockMvcRequestBuilders
                .patch("/airports/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportPartialUpdateDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(airportMapper).toAirport(any(AirportPartialUpdateDto.class));
        verify(airportService).updateById(anyInt(), any(Airport.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("update or create airport by id")
    void whenUpdateOrCreateById_thenUpdateAirport() {

        var airportSaveDto = new AirportSaveDto("Vatican's Airport", "Vatican", "Italy");
        var airportPartialUpdate = Airport.builder().name("Vatican's Airport").city("Vatican").country("Italy").build();
        var updatedAirport = Airport.builder()
                .id(airport.getId())
                .name(airportPartialUpdate.getName())
                .city(airportPartialUpdate.getCity())
                .country(airportPartialUpdate.getCountry())
                .build();
        var updatedAirportDto = new AirportDto(
                updatedAirport.getId(),
                updatedAirport.getName(),
                updatedAirport.getCity(),
                updatedAirport.getCountry()
        );

        doReturn(airportPartialUpdate).when(airportMapper).toAirport(airportSaveDto);
        doReturn(updatedAirport).when(airportService).updateOrCreateById(eq(airport.getId()), eq(airportPartialUpdate));
        doReturn(updatedAirportDto).when(airportMapper).toAirportDto(updatedAirport);

        var request = MockMvcRequestBuilders
                .put("/airports/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportSaveDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(airport.getId())))
                .andExpect(jsonPath("$.name", is(airportSaveDto.name())))
                .andExpect(jsonPath("$.city", is(airportSaveDto.city())))
                .andExpect(jsonPath("$.country", is(airportSaveDto.country())));

        verify(airportMapper).toAirport(any(AirportSaveDto.class));
        verify(airportService).updateOrCreateById(anyInt(), any(Airport.class));
        verify(airportMapper).toAirportDto(any(Airport.class));

    }

    @SneakyThrows
    @Test
    @DisplayName("update or create airport by not existing id")
    void whenUpdateOrCreateById_thenCreateAirport() {

        var airportSaveDto = new AirportSaveDto("Vatican's Airport", "Vatican", "Italy");
        var airportForSave = Airport.builder().name("Vatican's Airport").city("Vatican").country("Italy").build();
        var savedAirport = Airport.builder()
                .id(airport.getId() + 1)
                .name(airportForSave.getName())
                .city(airportForSave.getCity())
                .country(airportForSave.getCountry())
                .build();
        var saveAirportDto = new AirportDto(
                savedAirport.getId(),
                savedAirport.getName(),
                savedAirport.getCity(),
                savedAirport.getCountry()
        );

        doReturn(airportForSave).when(airportMapper).toAirport(airportSaveDto);
        doReturn(savedAirport).when(airportService).updateOrCreateById(eq(airport.getId()), eq(airportForSave));
        doReturn(saveAirportDto).when(airportMapper).toAirportDto(savedAirport);

        var request = MockMvcRequestBuilders
                .put("/airports/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportSaveDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(airportSaveDto.name())))
                .andExpect(jsonPath("$.city", is(airportSaveDto.city())))
                .andExpect(jsonPath("$.country", is(airportSaveDto.country())));

        verify(airportMapper).toAirport(any(AirportSaveDto.class));
        verify(airportService).updateOrCreateById(anyInt(), any(Airport.class));
        verify(airportMapper).toAirportDto(any(Airport.class));

    }

}