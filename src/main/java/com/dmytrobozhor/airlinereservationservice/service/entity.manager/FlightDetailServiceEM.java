package com.dmytrobozhor.airlinereservationservice.service.entity.manager;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Transactional
@Service
public class FlightDetailEM {

    @PersistenceContext
    private final EntityManager entityManager;

    @SneakyThrows
    public FlightDetail merge(FlightDetail flightDetail) {

        var sourceAirportId = flightDetail.getSourceAirport().getId();
        var destinationAirportId = flightDetail.getDestinationAirport().getId();

        Optional.ofNullable(
                entityManager.find(Airport.class, sourceAirportId)).orElseThrow((Supplier<Throwable>)
                () -> new EntityNotFoundException("Source airport not found by id" + sourceAirportId));

        Optional.ofNullable(
                entityManager.find(Airport.class, destinationAirportId)).orElseThrow((Supplier<Throwable>)
                () -> new EntityNotFoundException("Destination airport not found by id" + destinationAirportId));

        return entityManager.merge(flightDetail);
    }

}
