package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flight_cost", uniqueConstraints =
@UniqueConstraint(columnNames = {"seat_details_id", "valid_from_date_id"}))
public class FlightCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "seat_details_id", referencedColumnName = "id",
            nullable = false, insertable = false, updatable = false)
    private SeatDetail seatDetail;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "valid_from_date_id", referencedColumnName = "id",
            nullable = false, insertable = false, updatable = false)
    private Calendar validFromDate;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "valid_to_date_id", referencedColumnName = "id", nullable = false)
    private Calendar validToDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;

}
