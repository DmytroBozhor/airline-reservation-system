package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "flight_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "departure_date_time")
    private Timestamp departureDateTime;

    @Column(name = "arrival_date_time")
    private Timestamp arrivalDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "airplane_type")
    private AirplaneType airplaneType;

    //    TODO: make column validations for correct ddl generation  all over the project
    @ManyToOne(cascade = {CascadeType.MERGE})
//    @JoinColumn(name = "source_airport_id", referencedColumnName = "id", nullable = false)
    @JoinColumn(name = "source_airport_id", referencedColumnName = "id")
    private Airport sourceAirport;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "destination_airport_id", referencedColumnName = "id")
    private Airport destinationAirport;

}
