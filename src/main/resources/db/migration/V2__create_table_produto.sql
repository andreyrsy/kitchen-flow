create table produto
(
    id             BIGSERIAL primary key,
    nome           VARCHAR(100) NOT NULL UNIQUE,
    unidade_medida VARCHAR(20)  NOT NULL,
    categoria_id   BIGINT references categoria (id)
);