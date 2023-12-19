create table if not exists "flight_service"
(
    "id"           serial,
    "service_name" varchar(255) not null,
    constraint "flight_service_id_pk" primary key ("id"),
    constraint "service_name_check" check ( "service_name" in ('Food', 'French Wine', 'Wifi', 'Entertainment', 'Lounge'))
);