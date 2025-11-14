package br.gov.caixa.service;

import br.gov.caixa.model.SimulacaoInvestimento;
import br.gov.caixa.model.ProdutoInvestimento;
import br.gov.caixa.dto.SimulacaoRequest;
import br.gov.caixa.dto.SimulacaoResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SimulacaoService {

    @Inject
    PerfilRiscoService perfilRiscoService;

    @Transactional
    public SimulacaoResponse simularInvestimento(@Valid SimulacaoRequest request) {
        // Validar produto
        Optional<ProdutoInvestimento> produtoOpt = validarProduto(request);

        if (produtoOpt.isEmpty()) {
            throw new IllegalArgumentException("Produto não encontrado ou não atende aos critérios");
        }

        ProdutoInvestimento produto = produtoOpt.get();

        // Calcular simulação
        BigDecimal valorFinal = calcularValorFinal(request.valor, produto.rentabilidade, request.prazoMeses);

        // Persistir simulação
        SimulacaoInvestimento simulacao = new SimulacaoInvestimento();
        simulacao.clienteId = request.clienteId;
        simulacao.valor = request.valor;
        simulacao.prazoMeses = request.prazoMeses;
        simulacao.tipoProduto = request.tipoProduto;
        simulacao.produtoValidado = produto.nome;
        simulacao.valorFinal = valorFinal;
        simulacao.rentabilidadeEfetiva = produto.rentabilidade;
        simulacao.persist();

        // Construir resposta
        return construirResponse(produto, valorFinal, request.prazoMeses);
    }

    private Optional<ProdutoInvestimento> validarProduto(SimulacaoRequest request) {
        return ProdutoInvestimento.find(
                        "tipo = ?1 and ativo = true and (?2 >= valorMinimo or valorMinimo is null) and (?3 >= prazoMinimoMeses or prazoMinimoMeses is null)",
                        request.tipoProduto, request.valor, request.prazoMeses)
                .firstResultOptional();
    }

    private BigDecimal calcularValorFinal(BigDecimal valorInicial, BigDecimal rentabilidade, Integer prazoMeses) {
        // Fórmula: VF = VP * (1 + rentabilidade)^(prazo/12)
        double taxaMensal = rentabilidade.doubleValue() / 12;
        double fator = Math.pow(1 + taxaMensal, prazoMeses);
        BigDecimal valorFinal = valorInicial.multiply(BigDecimal.valueOf(fator));

        return valorFinal.setScale(2, RoundingMode.HALF_UP);
    }

    private SimulacaoResponse construirResponse(ProdutoInvestimento produto, BigDecimal valorFinal, Integer prazoMeses) {
        SimulacaoResponse response = new SimulacaoResponse();

        response.produtoValidado = new SimulacaoResponse.ProdutoValidado(
                produto.id,
                produto.nome,
                produto.tipo,
                produto.rentabilidade,
                produto.risco
        );

        response.resultadoSimulacao = new SimulacaoResponse.ResultadoSimulacao(
                valorFinal,
                produto.rentabilidade,
                prazoMeses
        );

        response.dataSimulacao = java.time.LocalDateTime.now();

        return response;
    }

    public List<SimulacaoInvestimento> listarSimulacoes() {
        return SimulacaoInvestimento.listAll();
    }
}
