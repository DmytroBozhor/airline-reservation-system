package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "seat_details")
public class SeatDetail implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private TravelClass travelClass;

//    TODO: Change in flyway the name of the table field so it can automap
    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_details_id")
    private FlightDetail flightDetail;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "seatDetail")
    private List<Reservation> reservations = new ArrayList<>();

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "seatDetail")
    private List<FlightCost> flightCosts = new ArrayList<>();

}
