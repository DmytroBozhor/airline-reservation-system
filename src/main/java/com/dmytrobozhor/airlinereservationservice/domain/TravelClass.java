package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.util.enums.TravelClassName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "travel_class")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private TravelClassName name;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ManyToMany
    @JoinTable(name = "service_offering",
            joinColumns = {@JoinColumn(name = "travel_class_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "flight_service_id", referencedColumnName = "id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<FlightService> flightServices = new ArrayList<>();

//    @OneToMany(mappedBy = "travelClass")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private List<ServiceOffering> serviceOfferings;

}
