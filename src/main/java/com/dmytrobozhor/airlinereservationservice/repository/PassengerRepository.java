package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.FlightDetail;
import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    //    TODO: refactor all the queries that have something like below
    @Query(nativeQuery = true,
            value = "select * from passenger " +
                    "where first_name = :#{#passenger.getFirstName()} " +
                    "and last_name = :#{#passenger.getLastName()} " +
                    "and email = :#{#passenger.getEmail()} " +
                    "and phone_number = :#{#passenger.getPhoneNumber()} " +
                    "and address = :#{#passenger.getAddress()} " +
                    "and city = :#{#passenger.getCity()} " +
                    "and state = :#{#passenger.getState()} " +
                    "and zipcode = :#{#passenger.getZipcode()} " +
                    "and country = :#{#passenger.getCountry()}")
    Optional<Passenger> findByAllFields(Passenger passenger);

}
