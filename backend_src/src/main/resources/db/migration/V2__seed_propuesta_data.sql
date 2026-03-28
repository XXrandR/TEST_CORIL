CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_users_username UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS bandeja (
    id BIGINT NOT NULL AUTO_INCREMENT,
    n_propuesta BIGINT,
    inicio DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS propuesta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    bandeja_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_propuesta_bandeja
        FOREIGN KEY (bandeja_id) REFERENCES bandeja(id)
);

CREATE TABLE IF NOT EXISTS propuesta_aprobacion (
    id BIGINT NOT NULL AUTO_INCREMENT,
    propuesta_id BIGINT,
    aprobacion VARCHAR(255),
    horario_ejecuto DATETIME,
    PRIMARY KEY (id),
    CONSTRAINT fk_aprobacion_propuesta
        FOREIGN KEY (propuesta_id) REFERENCES propuesta(id)
);

CREATE TABLE IF NOT EXISTS propuesta_detalle (
    guid VARCHAR(100) NOT NULL,
    nro_factura VARCHAR(255),
    fecha_desembolso DATETIME,
    fecha_cancelacion DATETIME,
    propuesta_id BIGINT,
    PRIMARY KEY (guid),
    CONSTRAINT fk_detalle_propuesta
        FOREIGN KEY (propuesta_id) REFERENCES propuesta(id)
);

DELETE FROM propuesta_detalle WHERE guid IN (
    'FN0010329593',
    'FN0010329594',
    'FN0010329595',
    'FN0010329295',
    'FN0010326162',
    'FN0010326244',
    'FN0008918633',
    'FN0008918631',
    'FN0008918634',
    'FN0008918550'
);

DELETE FROM propuesta_aprobacion WHERE propuesta_id IN (20021, 20022, 20036, 20038, 20039, 20041);

INSERT INTO bandeja (id, n_propuesta, inicio) VALUES
    (11107, 10449, '2025-08-20 10:28:15'),
    (20510, 20112, '2026-03-24 13:05:42'),
    (20511, 20109, '2026-03-24 13:06:29'),
    (20525, 20114, '2026-03-24 16:49:13'),
    (20527, 20133, '2026-03-24 17:00:39'),
    (20528, 20134, '2026-03-24 17:02:08'),
    (20530, 20132, '2026-03-24 17:13:15')
ON DUPLICATE KEY UPDATE
    n_propuesta = VALUES(n_propuesta),
    inicio = VALUES(inicio);

INSERT INTO propuesta (id, bandeja_id) VALUES
    (19904, 11107),
    (19906, 11107),
    (20021, 20510),
    (20022, 20511),
    (20036, 20525),
    (20038, 20527),
    (20039, 20528),
    (20041, 20530)
ON DUPLICATE KEY UPDATE
    bandeja_id = VALUES(bandeja_id);

INSERT INTO propuesta_aprobacion (propuesta_id, aprobacion, horario_ejecuto) VALUES
    (20021, 'AUDITORIA', '2026-03-24 16:14:17'),
    (20021, 'RIESGOS', '2026-03-24 16:56:44'),
    (20022, 'AUDITORIA', '2026-03-24 14:02:59'),
    (20022, 'RIESGOS', '2026-03-24 14:33:12'),
    (20036, 'AUDITORIA', '2026-03-24 17:04:56'),
    (20036, 'RIESGOS', '2026-03-24 17:13:46'),
    (20038, 'AUDITORIA', '2026-03-24 17:25:58'),
    (20038, 'RIESGOS', '2026-03-24 18:24:03'),
    (20039, 'AUDITORIA', '2026-03-24 17:56:12'),
    (20039, 'RIESGOS', '2026-03-24 18:24:03'),
    (20041, 'AUDITORIA', '2026-03-24 18:15:40'),
    (20041, 'RIESGOS', '2026-03-24 18:24:04');

INSERT INTO propuesta_detalle (guid, nro_factura, fecha_desembolso, fecha_cancelacion, propuesta_id) VALUES
    ('FN0010329593', 'F001-00007296', '2026-03-24 20:24:28', '2026-03-26 00:00:00', 20039),
    ('FN0010329594', 'F001-00007295', '2026-03-24 20:24:28', '2026-03-26 00:00:00', 20039),
    ('FN0010329595', 'F001-0022993', '2026-03-24 20:18:29', '2026-03-26 00:00:00', 20038),
    ('FN0010329295', 'E001-200', '2026-03-24 19:27:01', '2026-03-26 00:00:00', 20036),
    ('FN0010326162', 'E001-99', '2026-03-24 17:25:28', '2026-03-26 00:00:00', 20022),
    ('FN0010326244', 'E001-270', '2026-03-24 19:35:29', '2026-03-26 00:00:00', 20021),
    ('FN0008918633', 'R01 E001-180', '2026-03-20 18:01:46', '2026-03-20 00:00:00', 19906),
    ('FN0008918631', 'R01 E001-171', '2026-03-20 17:56:30', '2026-03-20 00:00:00', 19904),
    ('FN0008918634', 'R01 E001-108', '2026-03-20 17:56:30', '2026-03-20 00:00:00', 19904),
    ('FN0008918550', 'R01 E001-172', '2026-03-20 17:56:30', '2026-03-20 00:00:00', 19904)
ON DUPLICATE KEY UPDATE
    nro_factura = VALUES(nro_factura),
    fecha_desembolso = VALUES(fecha_desembolso),
    fecha_cancelacion = VALUES(fecha_cancelacion),
    propuesta_id = VALUES(propuesta_id);
