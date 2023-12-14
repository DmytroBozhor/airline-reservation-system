create table if not exists "airport"
(
    "id"      serial,
    "name"    varchar(255) not null,
    "city"    varchar(255) not null,
    "country" varchar(255) not null,
    constraint "id_pk" primary key ("id")
);