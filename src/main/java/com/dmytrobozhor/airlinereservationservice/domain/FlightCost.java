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

    @ManyToOne(targetEntity = SeatDetail.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "seat_details_id", referencedColumnName = "id")
    private SeatDetail seatDetail;

    @ManyToOne(targetEntity = Calendar.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "valid_from_date_id", referencedColumnName = "id")
    @MapsId(value = "validFromDateId")
    private Calendar validFromDate;

    @ManyToOne(targetEntity = Calendar.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "valid_to_date_id", referencedColumnName = "id")
    @MapsId(value = "validToDateId")
    private Calendar validToDate;

    @Column(name = "cost")
    private BigDecimal cost;
}

/*create table if not exists "flight_cost"
(
    "seat_details_id"    int            not null,
    "valid_from_date_id" date           not null,
    "valid_to_date_id"   date           not null,
    "cost"               decimal(12, 2) not null,
    constraint "seat_details_id_fk" foreign key ("seat_details_id") references "seat_details" ("id"),
    constraint "valid_from_date_id_fk" foreign key ("valid_from_date_id") references "calendar" ("day_date"),
    constraint "valid_to_date_id_fk" foreign key ("valid_to_date_id") references "calendar" ("day_date"),
    constraint "seat_details_id_valid_from_date_id_pk" primary key ("seat_details_id", "valid_from_date_id"),
    constraint "valid_from_date_id_valid_to_date_id_check" check ( valid_from_date_id < flight_cost.valid_to_date_id )
);*/