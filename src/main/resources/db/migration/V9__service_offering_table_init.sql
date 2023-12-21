create table if not exists "service_offering"
(
    "travel_class_id"   int     not null,
    "flight_service_id" int     not null,
    "offered"           boolean not null,
    "from_date"         timestamp,
    "to_date"           timestamp,
    constraint "travel_class_id_fk" foreign key ("travel_class_id") references "travel_class" ("id"),
    constraint "flight_service_id_fk" foreign key ("flight_service_id") references "flight_service" ("id"),
    constraint "travel_class_id_flight_service_id_pk" primary key ("travel_class_id", "flight_service_id"),
    constraint "from_date_to_date_check" check ( "from_date" < "to_date" )
);