CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    bairro VARCHAR(45) NOT NULL,
    rua VARCHAR(45) NOT NULL,
    numero VARCHAR(45) NOT NULL,
    complemento VARCHAR(45)
);