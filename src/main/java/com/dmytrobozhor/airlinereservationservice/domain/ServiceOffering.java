package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "service_offering")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOffering {

    //    TODO: replace this id pk with composite one
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(targetEntity = TravelClass.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "travel_class_id", referencedColumnName = "id")
    private TravelClass travelClass;

    @ManyToOne(targetEntity = TravelClass.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "flight_service_id", referencedColumnName = "id")
    private FlightService flightService;

    @Column(name = "offered")
    private Boolean offered;

    @Column(name = "from_date")
    private Timestamp formDate;

    @Column(name = "to_date")
    private Timestamp toDate;
}

/*create table if not exists "service_offering"
(
    "travel_class_id"   int     not null,
    "flight_service_id" int     not null,
    "offered"        boolean not null,
    "from_date"         timestamp,
    "to_date"           timestamp,
    constraint "travel_class_id_fk" foreign key ("travel_class_id") references "travel_class" ("id"),
    constraint "flight_service_id_fk" foreign key ("flight_service_id") references "flight_service" ("id"),
    constraint "travel_class_id_flight_service_id_pk" primary key ("travel_class_id", "flight_service_id"),
    constraint "from_date_to_date_check" check ( "from_date" < "to_date" )
);*/