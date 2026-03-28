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
