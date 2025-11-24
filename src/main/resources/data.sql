INSERT INTO estabelecimentos (id, nome, ceps_de_entrega)
VALUES (1, 'Tia Lu Lanches', '11111-111');
INSERT INTO estabelecimentos (id, nome, ceps_de_entrega)
VALUES (2, 'Pizzaria do Zé', '22222-222');

INSERT INTO produtos (id, nome, preco, estoque, estabelecimento_id)
VALUES (100, 'X-Burger', 25.50, 50, 1);
INSERT INTO produtos (id, nome, preco, estoque, estabelecimento_id)
VALUES (102, 'Batata Frita', 15.00, 30, 1);
INSERT INTO produtos (id, nome, preco, estoque, estabelecimento_id)
VALUES (200, 'Pizza de Calabresa', 45.00, 20, 2);

INSERT INTO usuarios (id, nome, email, ativo)
VALUES (1, 'Franklin', 'franklin@email.com', true);

INSERT INTO enderecos (id, cep, rua, numero, usuario_id)
VALUES (10, '11111-111', 'Rua da Faculdade', '123', 1);
INSERT INTO enderecos (id, cep, rua, numero, usuario_id)
VALUES (11, '22222-222', 'Avenida Principal', '456', 1);
