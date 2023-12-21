package com.dmytrobozhor.airlinereservationservice.util.compositeid;

import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class ServiceOfferingId implements Serializable {
    private Integer travelClassId;
    private Integer flightServiceId;
}

