create table if not exists "calendar"
(
    "day_date"        date,
    "business_day_yn" varchar(1) not null,
    constraint "id_pk" primary key ("day_date"),
    constraint "business_day_yn_check" check ( "business_day_yn" in ('Y', 'N'))
);