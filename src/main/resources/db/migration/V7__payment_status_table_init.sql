create table if not exists "payment_status"
(
    "id"             serial,
    "status_yn"      varchar(1)     not null,
    "due_date"       date           not null,
    "amount"         decimal(12, 2) not null,
    "reservation_id" int            not null,
    constraint "id_pk" primary key ("id"),
    constraint "reservation_id_fk" foreign key ("reservation_id") references "reservation" ("id"),
    constraint "status_yn_check" check ( "status_yn" in ('Y', 'N'))
);