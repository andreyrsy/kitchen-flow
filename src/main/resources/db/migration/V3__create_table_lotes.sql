create table lotes
(
    id            BIGSERIAL primary key,
    quantidade    INTEGER                        NOT NULL,
    data_entrada  DATE                           NOT NULL,
    data_validade DATE                           NOT NULL,
    produto_id    BIGINT references produto (id) NOT NULl
);