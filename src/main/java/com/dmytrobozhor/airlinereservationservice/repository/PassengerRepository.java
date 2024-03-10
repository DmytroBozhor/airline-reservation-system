package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

//    Optional<Passenger> findPassengerByPersonalInfoPhoneNumber(String phoneNumber);
//    Optional<Passenger> findPassengerByPhoneNumber(String phoneNumber);

}
