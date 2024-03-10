package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(nullable = false)
    private Timestamp reservationDateTime = Timestamp.from(Instant.now());

    @ManyToOne(optional = false)
    private Passenger passenger;

    @ManyToOne(optional = false)
    private SeatDetail seatDetail;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "reservation")
    private List<PaymentStatus> paymentStatuses = new ArrayList<>();

}
