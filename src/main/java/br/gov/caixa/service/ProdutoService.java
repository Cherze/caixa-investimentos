package br.gov.caixa.service;

import br.gov.caixa.model.ProdutoInvestimento;
import br.gov.caixa.dto.ProdutoRecomendadoResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoService {

    public List<ProdutoRecomendadoResponse> obterProdutosRecomendados(String perfil) {
        List<ProdutoInvestimento> produtos = ProdutoInvestimento
                .find("perfilRecomendado = ?1 and ativo = true", perfil)
                .list();

        return produtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProdutoRecomendadoResponse> obterTodosProdutos() {
        List<ProdutoInvestimento> produtos = ProdutoInvestimento
                .find("ativo = true")
                .list();

        return produtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProdutoRecomendadoResponse> obterProdutosPorTipo(String tipo) {
        List<ProdutoInvestimento> produtos = ProdutoInvestimento
                .find("tipo = ?1 and ativo = true", tipo)
                .list();

        return produtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoInvestimento criarProduto(ProdutoInvestimento produto) {
        produto.persist();
        return produto;
    }

    @Transactional
    public ProdutoInvestimento atualizarProduto(Long id, ProdutoInvestimento produtoAtualizado) {
        ProdutoInvestimento produto = ProdutoInvestimento.findById(id);
        if (produto != null) {
            produto.nome = produtoAtualizado.nome;
            produto.tipo = produtoAtualizado.tipo;
            produto.rentabilidade = produtoAtualizado.rentabilidade;
            produto.risco = produtoAtualizado.risco;
            produto.prazoMinimoMeses = produtoAtualizado.prazoMinimoMeses;
            produto.valorMinimo = produtoAtualizado.valorMinimo;
            produto.perfilRecomendado = produtoAtualizado.perfilRecomendado;
            produto.ativo = produtoAtualizado.ativo;
        }
        return produto;
    }

    @Transactional
    public boolean deletarProduto(Long id) {
        return ProdutoInvestimento.deleteById(id);
    }

    public ProdutoInvestimento obterProdutoPorId(Long id) {
        return ProdutoInvestimento.findById(id);
    }

    private ProdutoRecomendadoResponse toResponse(ProdutoInvestimento produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoRecomendadoResponse(
                produto.id,
                produto.nome,
                produto.tipo,
                produto.rentabilidade,
                produto.risco
        );
    }

    public List<ProdutoRecomendadoResponse> obterProdutosPorRisco(String risco) {
        List<ProdutoInvestimento> produtos = ProdutoInvestimento
                .find("risco = ?1 and ativo = true", risco)
                .list();

        return produtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProdutoRecomendadoResponse> obterProdutosPorRentabilidadeMinima(BigDecimal rentabilidadeMinima) {
        List<ProdutoInvestimento> produtos = ProdutoInvestimento
                .find("rentabilidade >= ?1 and ativo = true", rentabilidadeMinima)
                .list();

        return produtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
