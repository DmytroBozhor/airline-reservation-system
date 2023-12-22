package com.dmytrobozhor.airlinereservationservice.util.compositeid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightCostId implements Serializable {

    @Column(name = "valid_from_date_id")
    private Date validFromDateId;

    @Column(name = "valid_to_date_id")
    private Date validToDateId;

}

