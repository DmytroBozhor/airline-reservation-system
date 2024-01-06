package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.util.enums.ServiceName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flight_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_name")
    private ServiceName serviceName;

    @ManyToMany
    @JoinTable(name = "service_offering",
            joinColumns = {@JoinColumn(name = "flight_service_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "travel_class_id", referencedColumnName = "id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<TravelClass> travelClasses = new ArrayList<>();

}
