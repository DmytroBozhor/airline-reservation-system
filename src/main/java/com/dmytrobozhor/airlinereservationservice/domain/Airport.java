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
public class Airport implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "sourceAirport")
    private List<FlightDetail> flightDetailsAsSource = new ArrayList<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "destinationAirport")
    private List<FlightDetail> flightDetailsAsDestination = new ArrayList<>();

    public void addFlightDetailSource(FlightDetail flightDetail){
        this.flightDetailsAsSource.add(flightDetail);
        flightDetail.setSourceAirport(this);
    }

    public void addFlightDetailDestination(FlightDetail flightDetail){
        this.flightDetailsAsDestination.add(flightDetail);
        flightDetail.setDestinationAirport(this);
    }

}
