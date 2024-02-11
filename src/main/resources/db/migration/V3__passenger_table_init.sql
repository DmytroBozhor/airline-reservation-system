CREATE TABLE IF NOT EXISTS "passenger"
(
    "id"           BIGSERIAL,
    "first_name"   VARCHAR(255) NOT NULL,
    "last_name"    VARCHAR(255) NOT NULL,
    "email"        VARCHAR(255),
    "phone_number" VARCHAR(255) NOT NULL,
    "address"      VARCHAR(255),
    "city"         VARCHAR(255),
    "state"        VARCHAR(255),
    "zipcode"      VARCHAR(255),
    "country"      VARCHAR(255),
    CONSTRAINT "passenger_pk" PRIMARY KEY ("id"),
    CONSTRAINT "zipcode_check" CHECK (LENGTH("zipcode") = 5),
    CONSTRAINT "phone_number_check" CHECK (LENGTH("phone_number") = 10),
    CONSTRAINT "phone_number_unique" UNIQUE ("phone_number")
);