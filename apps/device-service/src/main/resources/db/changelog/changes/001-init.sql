CREATE TYPE device_type AS ENUM ('TEMPERATURE_SENSOR', 'LIGHT_SENSOR', 'GATE_SENSOR', 'VIDEO_CAMERA');
CREATE TYPE device_state AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TABLE IF NOT EXISTS device_group (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX idx_device_group_user_id_name ON device_group (user_id, name);

CREATE TABLE IF NOT EXISTS location (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR NOT NULL,
    parent_id BIGINT REFERENCES location (id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX idx_location_user_id_name ON location (user_id, name);

CREATE TABLE IF NOT EXISTS device (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    serial_number VARCHAR NOT NULL,
    name VARCHAR,
    type device_type NOT NULL,
    state device_state NOT NULL,
    location_id BIGINT REFERENCES location (id),
    device_group_id BIGINT REFERENCES device_group (id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);