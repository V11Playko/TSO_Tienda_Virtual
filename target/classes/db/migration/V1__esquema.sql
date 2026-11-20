-- Esquema inicial del catálogo virtual

CREATE TABLE usuario (
    id               BIGSERIAL PRIMARY KEY,
    nombre           VARCHAR(100) NOT NULL,
    correo           VARCHAR(150) NOT NULL UNIQUE,
    contrasena_hash  VARCHAR(100) NOT NULL,          -- hash BCrypt, nunca la clave en texto plano
    direccion_envio  VARCHAR(255) NOT NULL,
    creado_en        TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE TABLE categoria (
    id      BIGSERIAL PRIMARY KEY,
    nombre  VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE prenda (
    id            BIGSERIAL PRIMARY KEY,
    nombre        VARCHAR(120)  NOT NULL,
    categoria_id  BIGINT        NOT NULL REFERENCES categoria (id),
    genero        VARCHAR(10)   NOT NULL CHECK (genero IN ('Hombre', 'Mujer', 'Unisex')),
    talla         VARCHAR(20)   NOT NULL,           -- rango como texto, ej. 'S-XL', '28-36', 'Única'
    color         VARCHAR(40)   NOT NULL,
    material      VARCHAR(60)   NOT NULL,
    marca         VARCHAR(60)   NOT NULL,
    precio        NUMERIC(12,2) NOT NULL CHECK (precio >= 0),
    stock         INTEGER       NOT NULL CHECK (stock >= 0),
    imagen_url    VARCHAR(255),
    descripcion   VARCHAR(1000)
);

CREATE INDEX idx_prenda_nombre ON prenda (lower(nombre));
CREATE INDEX idx_prenda_categoria ON prenda (categoria_id);

CREATE TABLE carrito (
    id              BIGSERIAL PRIMARY KEY,
    usuario_id      BIGINT    NOT NULL UNIQUE REFERENCES usuario (id),  -- un carrito activo por usuario
    actualizado_en  TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE carrito_item (
    id          BIGSERIAL PRIMARY KEY,
    carrito_id  BIGINT      NOT NULL REFERENCES carrito (id) ON DELETE CASCADE,
    prenda_id   BIGINT      NOT NULL REFERENCES prenda (id),
    talla       VARCHAR(20) NOT NULL,
    cantidad    INTEGER     NOT NULL CHECK (cantidad > 0),
    UNIQUE (carrito_id, prenda_id, talla)
);

CREATE TABLE pedido (
    id          BIGSERIAL PRIMARY KEY,
    usuario_id  BIGINT        NOT NULL REFERENCES usuario (id),
    fecha       TIMESTAMP     NOT NULL DEFAULT now(),
    total       NUMERIC(12,2) NOT NULL CHECK (total >= 0),
    estado      VARCHAR(20)   NOT NULL DEFAULT 'CREADO'
);

CREATE TABLE pedido_detalle (
    id               BIGSERIAL PRIMARY KEY,
    pedido_id        BIGINT        NOT NULL REFERENCES pedido (id) ON DELETE CASCADE,
    prenda_id        BIGINT        NOT NULL REFERENCES prenda (id),
    talla            VARCHAR(20)   NOT NULL,
    cantidad         INTEGER       NOT NULL CHECK (cantidad > 0),
    precio_unitario  NUMERIC(12,2) NOT NULL CHECK (precio_unitario >= 0)  -- precio al momento de la compra
);
