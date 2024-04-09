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
public class FlightCost implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;

    @ManyToOne(optional = false)
    private SeatDetail seatDetail;

    @ManyToOne(optional = false)
    private Calendar validFromDate;

    @ManyToOne(optional = false)
    private Calendar validToDate;

}
