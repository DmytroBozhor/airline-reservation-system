package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", nullable = false)
    private Passenger passenger;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "seat_details_id", referencedColumnName = "id", nullable = false)
    private SeatDetail seatDetail;

    @Column(name = "reservation_date_time", nullable = false)
    @Builder.Default
    private Timestamp reservationDateTime = Timestamp.from(Instant.now());

}
