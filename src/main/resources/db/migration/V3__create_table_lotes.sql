create table lotes(
    id BIGSERIAL primary key,
    quantidade INTEGER,
    data_validade DATE,
    data_entrada DATE,
    produto_id BIGINT references produto (id)
);