package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.util.enums.ServiceName;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flight_service")
public class FlightService implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "flightService")
    private List<ServiceOffering> serviceOfferings = new ArrayList<>();

}
