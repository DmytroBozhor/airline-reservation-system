CREATE TABLE IF NOT EXISTS "service_offering"
(
    "id"                BIGSERIAL,
    "travel_class_id"   BIGINT  NOT NULL,
    "flight_service_id" BIGINT  NOT NULL,
    "offered"           BOOLEAN NOT NULL,
    "from_date"         TIMESTAMP,
    "to_date"           TIMESTAMP,
    CONSTRAINT "service_offering_id" PRIMARY KEY ("id"),
    CONSTRAINT "travel_class_id_fk" FOREIGN KEY ("travel_class_id") REFERENCES "travel_class" ("id"),
    CONSTRAINT "flight_service_id_fk" FOREIGN KEY ("flight_service_id") REFERENCES "flight_service" ("id"),
    CONSTRAINT "travel_class_id_flight_service_id_unique" UNIQUE ("travel_class_id", "flight_service_id")
);