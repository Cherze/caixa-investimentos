package br.gov.caixa.service;

import br.gov.caixa.model.ProdutoInvestimento;
import br.gov.caixa.dto.ProdutoRecomendadoResponse;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoService {

    public List<ProdutoRecomendadoResponse> obterProdutosRecomendados(String perfil) {
        return ProdutoInvestimento.find("perfilRecomendado = ?1 and ativo = true", perfil)
                .list()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProdutoRecomendadoResponse> obterTodosProdutos() {
        return ProdutoInvestimento.find("ativo = true")
                .list()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ProdutoRecomendadoResponse toResponse(ProdutoInvestimento produto) {
        return new ProdutoRecomendadoResponse(
                produto.id,
                produto.nome,
                produto.tipo,
                produto.rentabilidade,
                produto.risco
        );
    }
}
