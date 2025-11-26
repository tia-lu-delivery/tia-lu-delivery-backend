DELETE FROM pedidos;

INSERT INTO pedidos (numero_pedido, data_abertura, status_pedido, nome_restaurante, valor_total, usuario_id)
VALUES
('202511240001', '2025-11-24 18:30:00-03:00', 'Em Preparação', 'Pizza House', 89.90, 123),
('202511240002', '2025-11-24 15:20:00-03:00', 'Entregue', 'Burger Mania', 45.50, 123),
('202511240003', '2025-11-24 12:45:00-03:00', 'Cancelado', 'Sushi Place', 120.00, 123),
('202511230004', '2025-11-23 19:15:00-03:00', 'Entregue', 'Cantina Italiana', 75.30, 123),
('202511230005', '2025-11-23 14:10:00-03:00', 'Entregue', 'Mexican Food', 65.80, 123),
('202511220006', '2025-11-22 20:00:00-03:00', 'Entregue', 'Brasileirinho', 55.00, 123),
('202511220007', '2025-11-22 13:30:00-03:00', 'Entregue', 'Coffee Shop', 35.20, 123);

INSERT INTO pedidos (numero_pedido, data_abertura, status_pedido, nome_restaurante, valor_total, usuario_id)
VALUES
('202511240008', '2025-11-24 17:00:00-03:00', 'Entregue', 'Pizza House', 99.90, 456);