package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
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
@Table(name = "flight_details")
public class FlightDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_date_time", nullable = false)
    private Timestamp departureDateTime;

    @Column(name = "arrival_date_time", nullable = false)
    private Timestamp arrivalDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "airplane_type", nullable = false)
    private AirplaneType airplaneType;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "source_airport_id", referencedColumnName = "id", nullable = false)
    private Airport sourceAirport;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "destination_airport_id", referencedColumnName = "id", nullable = false)
    private Airport destinationAirport;

}
