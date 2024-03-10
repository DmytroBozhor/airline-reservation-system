package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.domain.embeddable.AdditionalInfo;
import com.dmytrobozhor.airlinereservationservice.domain.embeddable.PersonalInfo;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Passenger implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @Embedded
    private PersonalInfo personalInfo;

    @Valid
    @Embedded
    private AdditionalInfo additionalInfo;


    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "passenger")
    private List<Reservation> reservations = new ArrayList<>();

}
