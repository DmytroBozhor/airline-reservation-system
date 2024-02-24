package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.util.enums.AirplaneType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flight_details")
public class FlightDetail implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp departureDateTime;

    @Column(nullable = false)
    private Timestamp arrivalDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AirplaneType airplaneType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Airport sourceAirport;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Airport destinationAirport;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "flightDetail")
    private List<SeatDetail> seatDetails = new ArrayList<>();

}
