package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.domain.embeddable.AdditionalInfo;
import com.dmytrobozhor.airlinereservationservice.domain.embeddable.PersonalInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Embedded
//    private PersonalInfo personalInfo;

//    @Embedded
//    private AdditionalInfo additionalInfo;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    private String email;

    private String address;

    private String city;

    private String state;

    @Column(length = 5)
    private String zipcode;

    private String country;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "passenger")
    private List<Reservation> reservations = new ArrayList<>();

//    public Passenger(Long id, String firstName, String lastName, String phoneNumber, String email,
//                     String address, String city, String state, String zipcode, String country) {
//        this.id = id;
//        this.personalInfo = PersonalInfo.builder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .phoneNumber(phoneNumber)
//                .build();
//        this.additionalInfo = AdditionalInfo.builder()
//                .email(email)
//                .address(address)
//                .city(city)
//                .state(state)
//                .zipcode(zipcode)
//                .country(country)
//                .build();
//    }

}
