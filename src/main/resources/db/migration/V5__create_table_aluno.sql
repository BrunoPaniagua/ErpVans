CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(11),
    data_nasc DATE NOT NULL,
    nome_respo VARCHAR(45) NOT NULL,
    cpf_respo VARCHAR(11) NOT NULL,
    data_nasc_resp DATE NOT NULL,
    telefone VARCHAR(15),
    valor_pag DECIMAL(10,2),
    dia_paga INT,
    escola_id BIGINT NOT NULL,
    periodo_id BIGINT NOT NULL,

    CONSTRAINT fk_aluno_escola FOREIGN KEY (escola_id) REFERENCES escola(id),
    CONSTRAINT fk_aluno_periodo FOREIGN KEY (periodo_id) REFERENCES periodo(id)
);