package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.util.compositeid.ServiceOfferingId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "service_offering")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOffering {

    @EmbeddedId
    private ServiceOfferingId id;

    @ManyToOne(targetEntity = TravelClass.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "travel_class_id", referencedColumnName = "id")
    @MapsId(value = "travelClassId")
    private TravelClass travelClass;

    @ManyToOne(targetEntity = TravelClass.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "flight_service_id", referencedColumnName = "id")
    @MapsId(value = "flightServiceId")
    private FlightService flightService;

    @Column(name = "offered")
    private Boolean offered;

    @Column(name = "from_date")
    private Timestamp formDate;

    @Column(name = "to_date")
    private Timestamp toDate;
}
