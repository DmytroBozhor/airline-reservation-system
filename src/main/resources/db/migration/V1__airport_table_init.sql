CREATE TABLE IF NOT EXISTS "airport"
(
    "id"      BIGSERIAL,
    "name"    VARCHAR(255) NOT NULL,
    "city"    VARCHAR(255) NOT NULL,
    "country" VARCHAR(255) NOT NULL,
    CONSTRAINT "airport_pk" PRIMARY KEY ("id")
);