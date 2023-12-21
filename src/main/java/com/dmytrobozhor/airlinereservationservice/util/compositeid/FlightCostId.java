package com.dmytrobozhor.airlinereservationservice.util.compositeid;

import com.dmytrobozhor.airlinereservationservice.domain.Calendar;
import com.dmytrobozhor.airlinereservationservice.domain.FlightService;
import com.dmytrobozhor.airlinereservationservice.domain.TravelClass;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FlightCostId implements Serializable {
    private Integer validFromDateId;
    private Integer validToDateId;
}

