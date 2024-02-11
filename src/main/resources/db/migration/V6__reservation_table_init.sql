CREATE TABLE IF NOT EXISTS "reservation"
(
    "id"                    BIGSERIAL,
    "passenger_id"          BIGINT    NOT NULL,
    "seat_details_id"       BIGINT    NOT NULL,
    "reservation_date_time" TIMESTAMP NOT NULL,
    CONSTRAINT "reservation_pk" PRIMARY KEY ("id"),
    CONSTRAINT "passenger_id_fk" FOREIGN KEY ("passenger_id") REFERENCES "passenger" ("id"),
    CONSTRAINT "seat_details_id_fk" FOREIGN KEY ("seat_details_id") REFERENCES "seat_details" ("id")
);