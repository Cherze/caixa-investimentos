package br.gov.caixa.dto;

import java.math.BigDecimal;

public class ProdutoRecomendadoResponse {

    public Long id;
    public String nome;
    public String tipo;
    public BigDecimal rentabilidade;
    public String risco;

    // Construtor padrão
    public ProdutoRecomendadoResponse() {
    }

    // Construtor com parâmetros
    public ProdutoRecomendadoResponse(Long id, String nome, String tipo, BigDecimal rentabilidade, String risco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.rentabilidade = rentabilidade;
        this.risco = risco;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    @Override
    public String toString() {
        return "ProdutoRecomendadoResponse{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", rentabilidade=" + rentabilidade +
                ", risco='" + risco + '\'' +
                '}';
    }
}
