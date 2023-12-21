create table if not exists "calendar"
(
    "day_date"        date,
    "business_day" boolean not null,
    constraint "calendar_id_pk" primary key ("day_date")
);