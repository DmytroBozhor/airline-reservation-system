package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

//    @Override
//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "user_entity-graph")
//    <S extends Airport> S save(S entity);
//
//    @Override
//    @Query(nativeQuery = true, value = "delete from airport where id = :integer")
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    void deleteById(Integer integer);
//
//    @Override
//    @Query(nativeQuery = true,
//            value = "delete from airport " +
//            "where name = :#{#entity.getName()} " +
//            "and city = :#{#entity.getCity()} " +
//            "and country = :#{#entity.getCountry()}")
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    void delete(Airport entity);


//    @Query(nativeQuery = true,
//            value = "SELECT * FROM airport " +
//                    "WHERE name = :#{#airport.getName()} " +
//                    "AND city = :#{#airport.getCity()} " +
//                    "AND country = :#{#airport.getCountry()}")
//    Optional<Airport> findByAllFields(Airport airport);
//
//    @Transactional
//    @Query(nativeQuery = true, value = "DELETE FROM airport WHERE id = :id RETURNING *")
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    Optional<Airport> deleteByIdAndReturn(Long id);
}
