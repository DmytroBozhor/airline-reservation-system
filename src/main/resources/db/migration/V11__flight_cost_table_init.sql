create table if not exists "flight_cost"
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
);