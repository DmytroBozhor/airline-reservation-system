package com.dmytrobozhor.airlinereservationservice.integration;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportSaveDto;
import com.dmytrobozhor.airlinereservationservice.repository.AirportRepository;
import com.dmytrobozhor.airlinereservationservice.service.AbstractAirportService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.AirportMapper;
import com.dmytrobozhor.airlinereservationservice.web.controller.AirportController;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("integration")
@DisplayName("Airport Controller IT")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
// TODO: replace with real database
@AutoConfigureTestDatabase
//@TestPropertySource("classpath:../resources/application.yml")
//@PropertySource(value = "classpath:../resources/application.yml")
class AirportControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AbstractAirportService airportService;

    private Airport airport;

    private AirportDto airportDto;

    @BeforeEach
    void setUp() {

        airport = Airport.builder()
                .id(1)
                .name("Wong")
                .city("Bang")
                .country("Norway")
                .build();

        airportDto = new AirportDto(
                1,
                "Wong",
                "Bang",
                "Norway"
        );
    }

    @SneakyThrows
    @Test
    @DisplayName("save airport")
    void whenSaveAirportDto_thenReturnSavedAirportDto() {

        var request = MockMvcRequestBuilders
                .post("/airports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportDto));

//        TODO: decide what code is better

        /*var response = mockMvc.perform(request).andDo(print()).andReturn().getResponse().getContentAsString();
        var airportDtoResponse = objectMapper.readValue(response, AirportDto.class);
        assertAll(
                () -> assertThat(airportDtoResponse.id()).isNotNull(),
                () -> assertThat(airportDtoResponse.name()).isEqualTo(airportDto.name()),
                () -> assertThat(airportDtoResponse.city()).isEqualTo(airportDto.city()),
                () -> assertThat(airportDtoResponse.country()).isEqualTo(airportDto.country())
        );*/

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.id", notNullValue()),
                        jsonPath("$.name", is(airportDto.name())),
                        jsonPath("$.city", is(airportDto.city())),
                        jsonPath("$.country", is(airportDto.country()))
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("get airport by id")
    void whenGetById_returnFoundAirport() {

        airportService.save(airport);

        var request = MockMvcRequestBuilders
                .get("/airports/{id}", airport.getId());

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id", notNullValue()),
                        jsonPath("$.name", is(airportDto.name())),
                        jsonPath("$.city", is(airportDto.city())),
                        jsonPath("$.country", is(airportDto.country()))
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("get airport by not existing id")
    void whenGetById_returnThrowException() {

        var request = MockMvcRequestBuilders.get("/airports/{id}", airport.getId());

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @SneakyThrows
    @Test
    @DisplayName("delete airport by id")
    void whenDeleteById_thenReturnNothing() {

        airportService.save(airport);

        var request = MockMvcRequestBuilders.delete("/airports/{id}", airport.getId());

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @Test
    @DisplayName("get all airports")
    void whenGetAll_thenReturnAllAirports() {

        var airportTwo = Airport.builder()
                .id(2)
                .name("Chin")
                .city("Mu")
                .country("China")
                .build();

        var airports = List.of(airport, airportTwo);
        airportService.saveAll(airports);

        var request = MockMvcRequestBuilders
                .get("/airports");

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.size()", is(airports.size()))
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("update airport by id")
    void whenUpdateById_thenUpdateAirport() {

        var airportForUpdate = Airport.builder()
                .city("Vatican")
                .country("Italy")
                .build();

        airportService.save(airport);

        var request = MockMvcRequestBuilders
                .patch("/airports/{id}", airport.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportForUpdate));

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id", is(airport.getId())),
                        jsonPath("$.name", is(airport.getName())),
                        jsonPath("$.city", is(airportForUpdate.getCity())),
                        jsonPath("$.country", is(airportForUpdate.getCountry()))
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("update by not existing id")
    void whenUpdateById_thenThrowException() {

        var airportForUpdate = Airport.builder()
                .city("Vatican")
                .country("Italy")
                .build();

        var request = MockMvcRequestBuilders
                .patch("/airports/{id}", airport.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportForUpdate));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @SneakyThrows
    @Test
    @DisplayName("update or create airport by id")
    void whenUpdateOrCreateById_thenUpdateAirport() {

        var airportPartialUpdate = Airport.builder()
                .name("Vatican's Airport")
                .city("Vatican")
                .country("Italy")
                .build();

        airportService.save(airport);

        var request = MockMvcRequestBuilders
                .put("/airports/{id}", airport.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportPartialUpdate));

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id", is(airport.getId())),
                        jsonPath("$.name", is(airportPartialUpdate.getName())),
                        jsonPath("$.city", is(airportPartialUpdate.getCity())),
                        jsonPath("$.country", is(airportPartialUpdate.getCountry()))
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("update or create airport by not existing id")
    void whenUpdateOrCreateById_thenCreateAirport() {

        var airportPartialUpdate = Airport.builder()
                .name("Vatican's Airport")
                .city("Vatican")
                .country("Italy")
                .build();

        var request = MockMvcRequestBuilders
                .put("/airports/{id}", airport.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportPartialUpdate));

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id", notNullValue()),
                        jsonPath("$.name", is(airportPartialUpdate.getName())),
                        jsonPath("$.city", is(airportPartialUpdate.getCity())),
                        jsonPath("$.country", is(airportPartialUpdate.getCountry()))
                );
    }

    @AfterEach
    void clearDataBase() {
        airportService.deleteAll();
    }

}