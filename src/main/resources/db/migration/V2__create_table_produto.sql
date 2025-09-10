create table produto(
    id BIGSERIAL primary key,
    nome VARCHAR(100),
    unidade_medida VARCHAR(20),
    categoria_id BIGINT references categoria (id)
);