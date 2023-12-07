create table if not exists "passenger"
(
    "id"           serial,
    "first_name"   varchar(255) not null,
    "last_name"    varchar(255) not null,
    "email"        varchar(255),
    "phone_number" varchar(255) not null,
    "address"      varchar(255),
    "city"         varchar(255),
    "state"        varchar(255),
    "zipcode"      varchar(255),
    "country"      varchar(255),
    constraint "id_pk" primary key ("id"),
    constraint "email_check" check ("email" like '[a-z,0-9,_,-]%@[a-z]%.[a-z][a-z]%'),
    constraint "zipcode_check" check (length("zipcode") = 5),
    constraint "phone_number_unique" unique ("phone_number"),
    constraint "phone_number_check" check (length("phone_number") = 10)
);