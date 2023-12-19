create table if not exists "travel_class"
(
    "id"       serial,
    "name"     varchar(255) not null,
    "capacity" int          not null,
    constraint "travel_class_id_pk" primary key ("id"),
    constraint "name_check" check ( "name" in ('First Class', 'Business Class', 'Premium Economy', 'Economy Class',
                                               'Basic Economy'))
);