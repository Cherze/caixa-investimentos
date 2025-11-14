INSERT INTO produtos_investimento (nome, tipo, rentabilidade, risco, prazo_minimo_meses, valor_minimo, perfil_recomendado, ativo)
VALUES
('CDB Banco ABC 2025', 'CDB', 0.115, 'Baixo', 6, 1000.00, 'Conservador', true),
('LCI Banco XYZ', 'LCI', 0.095, 'Baixo', 12, 5000.00, 'Conservador', true),
('Fundo Ações Growth', 'Fundo', 0.185, 'Alto', 24, 10000.00, 'Agressivo', true),
('Tesouro Direto IPCA+', 'Tesouro', 0.065, 'Baixo', 36, 100.00, 'Conservador', true),
('Fundo Multimercado Balanced', 'Fundo', 0.125, 'Moderado', 12, 2000.00, 'Moderado', true),
('LCA Agro Brasil', 'LCA', 0.085, 'Baixo', 18, 3000.00, 'Moderado', true);

INSERT INTO historico_investimentos (cliente_id, tipo, valor, rentabilidade, data)
VALUES
(123, 'CDB', 5000.00, 0.12, '2025-01-15'),
(123, 'Fundo Multimercado', 3000.00, 0.08, '2025-03-10'),
(123, 'LCI', 7000.00, 0.095, '2025-06-20'),
(456, 'Tesouro Direto', 2000.00, 0.065, '2025-02-01'),
(456, 'Fundo Ações', 15000.00, 0.185, '2025-04-15');