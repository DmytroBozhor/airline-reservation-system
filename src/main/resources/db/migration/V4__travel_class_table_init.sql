create table if not exists "travel_class"
(
    "id"       serial,
    "name"     varchar(255) not null,
    "capacity" int          not null,
    constraint "travel_class_id_pk" primary key ("id"),
    constraint "name_check" check ( "name" in ('FIRST_CLASS', 'BUSINESS_CLASS', 'PREMIUM_ECONOMY', 'ECONOMY_CLASS',
                                               'BASIC_ECONOMY'))
);