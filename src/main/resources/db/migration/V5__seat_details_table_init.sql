CREATE TABLE IF NOT EXISTS "seat_details"
(
    "id"                BIGSERIAL,
    "travel_class_id"   BIGINT NOT NULL,
    "flight_details_id" BIGINT NOT NULL,
    CONSTRAINT "seat_details_pk" PRIMARY KEY ("id"),
    CONSTRAINT "travel_class_id_fk" FOREIGN KEY ("travel_class_id") REFERENCES "travel_class" ("id"),
    CONSTRAINT "flight_details_id_fk" FOREIGN KEY ("flight_details_id") REFERENCES "flight_details" ("id")
);