package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    @Override
//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "user_entity-graph")
    <S extends Airport> S save(S entity);
}
