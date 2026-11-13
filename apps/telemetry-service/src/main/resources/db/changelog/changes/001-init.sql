CREATE EXTENSION IF NOT EXISTS timescaledb;

CREATE TABLE IF NOT EXISTS device_telemetry (
    id BIGSERIAL NOT NULL,
    device_id BIGINT NOT NULL,
    payload JSONB NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),

    PRIMARY KEY (created_at, id)
);

SELECT create_hypertable(
    'device_telemetry',
    by_range('created_at'),
    if_not_exists => TRUE
);

CREATE UNIQUE INDEX idx_device_telemetry_created_at_id ON device_telemetry (device_id, created_at DESC);
