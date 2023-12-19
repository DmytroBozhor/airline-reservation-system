create table if not exists "reservation"
(
    "id"                    serial,
    "passenger_id"          int       not null,
    "seat_details_id"       int       not null,
    "reservation_date_time" timestamp not null,
    constraint "reservation_id_pk" primary key ("id"),
    constraint "passenger_id_fk" foreign key ("passenger_id") references "passenger" ("id"),
    constraint "seat_details_id_fk" foreign key ("seat_details_id") references "seat_details" ("id")
);