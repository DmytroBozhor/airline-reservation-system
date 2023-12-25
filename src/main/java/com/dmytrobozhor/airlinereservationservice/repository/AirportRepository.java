package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    @Override
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Transactional
//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "user_entity-graph")
    <S extends Airport> S save(S entity);

}
