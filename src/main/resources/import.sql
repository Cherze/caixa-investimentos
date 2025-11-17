
-- SQLite usa 1 para true e 0 para false
INSERT INTO produtos_investimento (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, valor_minimo, perfil_recomendado, ativo)
VALUES (1, 'CDB Banco ABC 2025', 'CDB', 0.115, 'Baixo', 6, 1000.00, 'Conservador', 1);

INSERT INTO produtos_investimento (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, valor_minimo, perfil_recomendado, ativo)
VALUES (2, 'LCI Banco XYZ', 'LCI', 0.095, 'Baixo', 12, 5000.00, 'Conservador', 1);

INSERT INTO produtos_investimento (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, valor_minimo, perfil_recomendado, ativo)
VALUES (3, 'Fundo Ações Growth', 'Fundo', 0.185, 'Alto', 24, 10000.00, 'Agressivo', 1);

INSERT INTO produtos_investimento (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, valor_minimo, perfil_recomendado, ativo)
VALUES (4, 'Tesouro Direto IPCA+', 'Tesouro', 0.065, 'Baixo', 36, 100.00, 'Conservador', 1);

INSERT INTO produtos_investimento (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, valor_minimo, perfil_recomendado, ativo)
VALUES (5, 'Fundo Multimercado Balanced', 'Fundo', 0.125, 'Moderado', 12, 2000.00, 'Moderado', 1);

INSERT INTO produtos_investimento (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, valor_minimo, perfil_recomendado, ativo)
VALUES (6, 'LCA Agro Brasil', 'LCA', 0.085, 'Baixo', 18, 3000.00, 'Moderado', 1);

-- Inserir histórico com IDs MANUAIS
INSERT INTO historico_investimentos (id, cliente_id, tipo, valor, rentabilidade, data)
VALUES (1, 123, 'CDB', 5000.00, 0.12, '2025-01-15');

INSERT INTO historico_investimentos (id, cliente_id, tipo, valor, rentabilidade, data)
VALUES (2, 123, 'Fundo Multimercado', 3000.00, 0.08, '2025-03-10');

INSERT INTO historico_investimentos (id, cliente_id, tipo, valor, rentabilidade, data)
VALUES (3, 123, 'LCI', 7000.00, 0.095, '2025-06-20');

INSERT INTO historico_investimentos (id, cliente_id, tipo, valor, rentabilidade, data)
VALUES (4, 456, 'Tesouro Direto', 2000.00, 0.065, '2025-02-01');

INSERT INTO historico_investimentos (id, cliente_id, tipo, valor, rentabilidade, data)
VALUES (5, 456, 'Fundo Ações', 15000.00, 0.185, '2025-04-15');

-- TESTE NO H2
--INSERT INTO produtos_investimento (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, valor_minimo, perfil_recomendado, ativo)
--VALUES
--(nextval('produtos_investimento_seq'), 'CDB Caixa 2026', 'CDB', 0.115, 'Baixo', 6, 1000.00, 'Conservador', true),
--(nextval('produtos_investimento_seq'), 'LCI Caixa', 'LCI', 0.095, 'Baixo', 12, 5000.00, 'Conservador', true),
--(nextval('produtos_investimento_seq'), 'Fundo Ações', 'Fundo', 0.185, 'Alto', 24, 10000.00, 'Agressivo', true),
--(nextval('produtos_investimento_seq'), 'Tesouro Direto IPCA+', 'Tesouro', 0.065, 'Baixo', 36, 100.00, 'Conservador', true),
--(nextval('produtos_investimento_seq'), 'Fundo Multimercado Balanced', 'Fundo', 0.125, 'Moderado', 12, 2000.00, 'Moderado', true),
--(nextval('produtos_investimento_seq'), 'LCA Agro Brasil', 'LCA', 0.085, 'Baixo', 18, 3000.00, 'Moderado', true);

--INSERT INTO historico_investimentos (id, cliente_id, tipo, valor, rentabilidade, data)
--VALUES
--(nextval('historico_investimentos_seq'), 123, 'CDB', 5000.00, 0.12, '2025-01-15'),
--(nextval('historico_investimentos_seq'), 123, 'Fundo Multimercado', 3000.00, 0.08, '2025-03-10'),
--(nextval('historico_investimentos_seq'), 123, 'LCI', 7000.00, 0.095, '2025-06-20'),
--(nextval('historico_investimentos_seq'), 456, 'Tesouro Direto', 2000.00, 0.065, '2025-02-01'),
--(nextval('historico_investimentos_seq'), 456, 'Fundo Ações', 15000.00, 0.185, '2025-04-15');