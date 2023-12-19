create table if not exists "flight_details"
(
    "id"                   serial,
    "source_airport_id"    int          not null,
    destination_airport_id int          not null,
    "departure_date_time"  timestamp    not null,
    "arrival_date_time"    timestamp    not null,
    "airplane_type"        varchar(255) not null,
    constraint "flight_details_id_pk" primary key ("id"),
    constraint "source_airport_id_fk" foreign key ("source_airport_id") references "airport" ("id"),
    constraint "destination_airport_id_fk" foreign key (destination_airport_id) references "airport" ("id"),
    constraint "date_check" check ("departure_date_time" < "arrival_date_time"),
    constraint "airport_check" check ("source_airport_id" != destination_airport_id),
    constraint "airplane_type_check" check ("airplane_type" in ('Airbus A380', 'Boeing 747'))
);