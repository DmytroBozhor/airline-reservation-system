package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    @Override
//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "user_entity-graph")
    <S extends Airport> S save(S entity);

    @Override
    @Query(nativeQuery = true, value = "delete from airport where id = :integer")
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void deleteById(Integer integer);

    @Override
    @Query(nativeQuery = true,
            value = "delete from airport " +
            "where name = :#{#entity.getName()} " +
            "and city = :#{#entity.getCity()} " +
            "and country = :#{#entity.getCountry()}")
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void delete(Airport entity);
}
