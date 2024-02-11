CREATE TABLE IF NOT EXISTS "flight_details"
(
    "id"                     BIGSERIAL,
    "source_airport_id"      BIGINT       NOT NULL,
    "destination_airport_id" BIGINT       NOT NULL,
    "departure_date_time"    TIMESTAMP    NOT NULL,
    "arrival_date_time"      TIMESTAMP    NOT NULL,
    "airplane_type"          VARCHAR(255) NOT NULL,
    CONSTRAINT "flight_details_pk" PRIMARY KEY ("id"),
    CONSTRAINT "source_airport_id_fk" FOREIGN KEY ("source_airport_id") REFERENCES "airport" ("id"),
    CONSTRAINT "destination_airport_id_fk" FOREIGN KEY (destination_airport_id) REFERENCES "airport" ("id"),
    CONSTRAINT "airplane_type_check" CHECK ("airplane_type" IN ('AIRBUS_A380', 'BOEING_747'))
);