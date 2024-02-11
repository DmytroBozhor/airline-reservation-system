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
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean offered;

    @Column(name = "from_date")
    private Timestamp formDate;

    @Column(name = "to_date")
    private Timestamp toDate;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "travel_class_id", referencedColumnName = "id",
            nullable = false, insertable = false, updatable = false)
    private TravelClass travelClass;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "flight_service_id", referencedColumnName = "id",
            nullable = false, insertable = false, updatable = false)
    private FlightService flightService;

}
