create table if not exists "flight_details"
(
    "id"                  serial,
    "source_airport"      int       not null,
    "destination_airport" int       not null,
    "departure_date_time" timestamp not null,
    "arrival_date_time"   timestamp not null,
    constraint "id_pk" primary key ("id"),
    constraint "source_airport_fk" foreign key ("source_airport") references "airport" ("id"),
    constraint "destination_airport_fk" foreign key ("destination_airport") references "airport" ("id"),
    constraint "date_check" check ("departure_date_time" < "arrival_date_time"),
    constraint "airport_check" check ("source_airport" != "destination_airport")
);