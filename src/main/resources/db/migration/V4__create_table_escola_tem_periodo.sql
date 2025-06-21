CREATE TABLE escola_tem_periodo (
    escola_id BIGINT,
    periodo_id BIGINT,

    PRIMARY KEY (escola_id, periodo_id),

    CONSTRAINT fk_escola_tem_periodo_escola FOREIGN KEY (escola_id) REFERENCES escola(id),
    CONSTRAINT fk_escola_tem_periodo_periodo FOREIGN KEY (periodo_id) REFERENCES periodo(id)
);