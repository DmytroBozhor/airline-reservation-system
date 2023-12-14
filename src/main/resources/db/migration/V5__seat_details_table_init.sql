create table if not exists "seat_details"
(
    "id"                serial,
    "travel_class_id"   int not null,
    "flight_details_id" int not null,
    constraint "id_pk" primary key ("id"),
    constraint "travel_class_id_fk" foreign key ("travel_class_id") references "travel_class" ("id"),
    constraint "flight_details_id_fk" foreign key ("flight_details_id") references "flight_details" ("id")
);