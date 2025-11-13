CREATE TABLE owner_partner (
    id_socio VARCHAR(36) PRIMARY KEY,
    cpf VARCHAR(11),
    rg VARCHAR(20) NOT NULL,
    orgao_emissor_rg VARCHAR(20) NOT NULL,
    nome_completo VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone VARCHAR(13) NOT NULL,
    id_estabelecimento VARCHAR(36) NOT NULL
);

CREATE TABLE establishment (
    id_estabelecimento VARCHAR(36) PRIMARY KEY
)