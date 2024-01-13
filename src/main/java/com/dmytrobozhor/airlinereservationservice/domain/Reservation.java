package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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
