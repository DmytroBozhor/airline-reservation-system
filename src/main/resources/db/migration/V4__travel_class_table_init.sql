CREATE TABLE IF NOT EXISTS "travel_class"
(
    "id"       BIGSERIAL,
    "name"     VARCHAR(255) NOT NULL,
    "capacity" INT          NOT NULL,
    CONSTRAINT "travel_class_id" PRIMARY KEY ("id"),
    CONSTRAINT "name_check" CHECK ( "name" IN ('FIRST_CLASS', 'BUSINESS_CLASS', 'PREMIUM_ECONOMY', 'ECONOMY_CLASS',
                                               'BASIC_ECONOMY'))
);