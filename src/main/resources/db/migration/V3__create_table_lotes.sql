create table lotes(
    id BIGSERIAL primary key,
    quantidade DECIMAL,
    data_validade DATE,
    data_entrada DATE,
    produto_id BIGINT references produto (id)
);