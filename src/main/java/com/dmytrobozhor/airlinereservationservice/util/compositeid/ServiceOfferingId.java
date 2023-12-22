package com.dmytrobozhor.airlinereservationservice.util.compositeid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOfferingId implements Serializable {

    @Column(name = "travel_class_id")
    private Integer travelClassId;

    @Column(name = "flight_service_id")
    private Integer flightServiceId;

}

