package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Calendar implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Date date;

    @Column(nullable = false)
    private Boolean businessDay;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "validFromDate")
    private List<FlightCost> flightCostsFromDate = new ArrayList<>();

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "validToDate")
    private List<FlightCost> flightCostsToDate = new ArrayList<>();

}
