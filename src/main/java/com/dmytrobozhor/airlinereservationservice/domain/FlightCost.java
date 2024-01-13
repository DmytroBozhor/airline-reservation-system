package com.dmytrobozhor.airlinereservationservice.domain;

import com.dmytrobozhor.airlinereservationservice.util.compositeid.FlightCostId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "flight_cost")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightCost {

    @EmbeddedId
    private FlightCostId id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "seat_details_id", referencedColumnName = "id",
            nullable = false, insertable = false, updatable = false)
    @MapsId(value = "seatDetailId")
    private SeatDetail seatDetail;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "valid_from_date_id", referencedColumnName = "day_date",
            nullable = false, insertable = false, updatable = false)
    @MapsId(value = "validFromDateId")
    private Calendar validFromDate;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "valid_to_date_id", referencedColumnName = "day_date", nullable = false)
    private Calendar validToDate;

    @Column(name = "cost", nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;

}
