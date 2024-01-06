package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelClassRepository extends JpaRepository<TravelClass, Integer> {

    @Query(nativeQuery = true,
            value = "select * from travel_class " +
                    "where name = :#{#travelClass.getName().toString()} " +
                    "and capacity = :#{#travelClass.getCapacity()} ")
    Optional<TravelClass> findByAllFields(TravelClass travelClass);

}
