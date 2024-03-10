package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "service_offering", uniqueConstraints =
@UniqueConstraint(columnNames = {"travel_class_id", "flight_service_id"}))
public class ServiceOffering implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean offered;

    private Timestamp fromDate;

    private Timestamp toDate;

    @ManyToOne(optional = false)
    private TravelClass travelClass;

    @ManyToOne(optional = false)
    private FlightService flightService;

}
