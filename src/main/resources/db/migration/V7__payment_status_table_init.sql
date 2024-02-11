CREATE TABLE IF NOT EXISTS "payment_status"
(
    "id"             BIGSERIAL,
    "status"         VARCHAR(32)    NOT NULL,
    "due_date"       DATE           NOT NULL,
    "amount"         DECIMAL(12, 2) NOT NULL,
    "reservation_id" BIGINT         NOT NULL,
    CONSTRAINT "payment_status_pk" PRIMARY KEY ("id"),
    CONSTRAINT "reservation_id_fk" FOREIGN KEY ("reservation_id") REFERENCES "reservation" ("id"),
    CONSTRAINT "status_yn_check" CHECK ( "status" IN
                                         ('PENDING', 'COMPLETE', 'REFUNDED', 'FAILED', 'ABANDONED', 'REVOKED',
                                          'PREAPPROVED', 'ON_HOLD', 'CANCELLED'))
);