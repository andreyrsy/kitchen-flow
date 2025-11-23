create table lotes(
    id BIGSERIAL primary key,
    quantidade INTEGER,
    data_entrada DATE,
    data_validade DATE,
    produto_id BIGINT references produto (id)
);