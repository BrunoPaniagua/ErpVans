CREATE TABLE escola (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco_id BIGINT NOT NULL,

    CONSTRAINT fk_escola_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);