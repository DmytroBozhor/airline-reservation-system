package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seat_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "travel_class_id", referencedColumnName = "id", nullable = false)
    private TravelClass travelClass;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "flight_details_id", referencedColumnName = "id", nullable = false)
    private FlightDetail flightDetail;

}
