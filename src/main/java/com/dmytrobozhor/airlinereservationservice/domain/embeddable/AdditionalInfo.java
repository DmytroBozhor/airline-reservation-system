package com.dmytrobozhor.airlinereservationservice.domain.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class AdditionalInfo {

    private String email;

    private String address;

    private String city;

    private String state;

    @Column(length = 5)
    private String zipcode;

    private String country;

}
