CREATE TABLE IF NOT EXISTS "flight_cost"
(
    "id"                 BIGSERIAL,
    "seat_details_id"    BIGINT         NOT NULL,
    "valid_from_date_id" BIGINT         NOT NULL,
    "valid_to_date_id"   BIGINT         NOT NULL,
    "cost"               DECIMAL(12, 2) NOT NULL,
    CONSTRAINT "flight_cost_id" PRIMARY KEY ("id"),
    CONSTRAINT "seat_details_id_fk" FOREIGN KEY ("seat_details_id") REFERENCES "seat_details" ("id"),
    CONSTRAINT "valid_from_date_id_fk" FOREIGN KEY ("valid_from_date_id") REFERENCES "calendar" ("id"),
    CONSTRAINT "valid_to_date_id_fk" FOREIGN KEY ("valid_to_date_id") REFERENCES "calendar" ("id"),
    CONSTRAINT "seat_details_id_valid_from_date_id_unique" UNIQUE ("seat_details_id", "valid_from_date_id")
);