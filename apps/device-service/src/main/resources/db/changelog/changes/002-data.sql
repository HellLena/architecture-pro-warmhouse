INSERT INTO device_group(user_id, name) VALUES
    (1, 'Модуль отопления');

INSERT INTO location(id, user_id, name) VALUES
    (1, 1, 'Дом 1');

INSERT INTO location(id, user_id, name, parent_id) VALUES
    (2, 1, 'Спальня', 1),
    (3, 1, 'Кухня', 1);

SELECT setval(
   pg_get_serial_sequence('location', 'id'),
   (SELECT MAX(id) FROM location)
);

INSERT INTO device(id, user_id, serial_number, type, state, location_id) VALUES
    (1,1, 'e2dc065a-7098-4be4-9521-4d1ef027f0d0', 'TEMPERATURE_SENSOR', 'ACTIVE', 2),
    (2, 1, 'fe6194a3-ce3f-42be-b9c9-06dafb1190b2', 'TEMPERATURE_SENSOR', 'ACTIVE', 3);

SELECT setval(
    pg_get_serial_sequence('device', 'id'),
    (SELECT MAX(id) FROM device)
);

CREATE CAST (character varying AS device_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying AS device_state) WITH INOUT AS IMPLICIT;