package com.dmytrobozhor.airlinereservationservice.service.entity.manager;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Transactional
@Service
public class FlightDetailServiceEM implements AbstractFlightDetailServiceEM {

    @PersistenceContext
    private final EntityManager entityManager;

    @SneakyThrows
    @Override
    public FlightDetail save(FlightDetail flightDetail) {
//        var session = entityManager.unwrap(Session.class);
        mergeAirports(flightDetail);
        entityManager.persist(flightDetail);
        return flightDetail;
    }

    @SneakyThrows
    @Override
    public FlightDetail update(FlightDetail flightDetail) {
        mergeAirports(flightDetail);
        return entityManager.merge(flightDetail);
    }

//    @Override
//    public FlightDetail delete(Long id) {
//        String sql = "DELETE FROM flight_details WHERE id = :id RETURNING *";
//        var flightDetail = entityManager.createNativeQuery(sql, FlightDetail.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        return (FlightDetail) flightDetail;
//    }

    private void mergeAirports(FlightDetail flightDetail) throws Throwable {
        var sourceAirportId = flightDetail.getSourceAirport().getId();
        var destinationAirportId = flightDetail.getDestinationAirport().getId();

        Optional.ofNullable(entityManager.find(Airport.class, sourceAirportId))
                .orElseThrow((Supplier<Throwable>)
                        () -> new EntityNotFoundException("Source airport not found by id: " + sourceAirportId));

        Optional.ofNullable(entityManager.find(Airport.class, destinationAirportId))
                .orElseThrow((Supplier<Throwable>)
                        () -> new EntityNotFoundException("Destination airport not found by id: " + destinationAirportId));

        var mergedSourceAirport = entityManager.merge(flightDetail.getSourceAirport());
        var mergedDestinationAirport = entityManager.merge(flightDetail.getDestinationAirport());

        flightDetail.setSourceAirport(mergedSourceAirport);
        flightDetail.setDestinationAirport(mergedDestinationAirport);
    }

}
