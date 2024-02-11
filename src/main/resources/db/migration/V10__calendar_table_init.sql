CREATE TABLE IF NOT EXISTS "calendar"
(
    "id"           BIGSERIAL,
    "day_date"     DATE    NOT NULL,
    "business_day" BOOLEAN NOT NULL,
    CONSTRAINT "calendar_pk" PRIMARY KEY ("id"),
    CONSTRAINT "day_date_unique" UNIQUE ("day_date")
);