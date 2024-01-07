package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.ServiceOffering;
import com.dmytrobozhor.airlinereservationservice.util.compositeid.ServiceOfferingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, ServiceOfferingId> {

    @Query(nativeQuery = true,
            value = "select * from service_offering " +
                    "where travel_class_id = :#{#serviceOffering.getTravelClass().getId()} " +
                    "and flight_service_id = :#{#serviceOffering.getFlightService().getId()} " +
                    "and offered = :#{#serviceOffering.getOffered()} " +
                    "and from_date = :#{#serviceOffering.getFormDate()} " +
                    "and to_date = :#{#serviceOffering.getToDate()}")
    Optional<ServiceOffering> findByAllFields(ServiceOffering serviceOffering);

}
