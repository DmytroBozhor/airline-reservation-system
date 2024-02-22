package com.dmytrobozhor.airlinereservationservice.util.mappers.flight;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Component
public class FlightDetailAssociationMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public Airport toAirport(Long id) {
        if (id == null) {
            return null;
        }

        return Optional.ofNullable(entityManager.find(Airport.class, id))
                .orElseThrow(() -> new EntityNotFoundException("No airport found by id " + id));
    }

    public Long toId(Airport airport) {
        return airport != null ? airport.getId() : null;
    }
}
