CREATE TABLE IF NOT EXISTS "flight_service"
(
    "id"           BIGSERIAL,
    "service_name" VARCHAR(255)   NOT NULL,
    "cost"         DECIMAL(12, 2) NOT NULL,
    CONSTRAINT "flight_service_pk" PRIMARY KEY ("id")
);