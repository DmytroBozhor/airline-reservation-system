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

    @ManyToOne(targetEntity = TravelClass.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "travel_class_id", referencedColumnName = "id")
    private TravelClass travelClass;

    @ManyToOne(targetEntity = FlightDetail.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "flight_details_id", referencedColumnName = "id")
    private FlightDetail flightDetail;

}
