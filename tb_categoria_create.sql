-- Script SQL para criação da tabela TB_CATEGORIA

CREATE TABLE TB_CATEGORIA (
    -- Chave Primária (PK)
    id_categoria VARCHAR(36) PRIMARY KEY NOT NULL,

    -- Chave Estrangeira (FK) para o Cardápio
    -- Assumindo que o ID do Cardápio também é um UUID/VARCHAR(36)
    id_cardapio VARCHAR(36) NOT NULL,

    -- Campos da Categoria
    nome_categoria VARCHAR(255) NOT NULL,
    descricao VARCHAR(500), -- Opcional
    ordem INTEGER NOT NULL,
    disponivel BOOLEAN NOT NULL,

    -- Restrição de Unicidade (CA 1.4)
    -- Garante que o nome da categoria seja único DENTRO de um cardápio específico
    CONSTRAINT uk_categoria_cardapio UNIQUE (id_cardapio, nome_categoria)

    -- A chave estrangeira real (FOREIGN KEY) dependeria da existência da tabela TB_CARDAPIO.
    -- Exemplo:
    -- CONSTRAINT fk_categoria_cardapio FOREIGN KEY (id_cardapio) REFERENCES TB_CARDAPIO(id_cardapio)
);

-- Índice para otimizar buscas por cardápio
CREATE INDEX idx_categoria_cardapio ON TB_CATEGORIA (id_cardapio);