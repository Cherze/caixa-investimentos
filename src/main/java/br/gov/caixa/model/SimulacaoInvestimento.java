package br.gov.caixa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulacoes_investimento")
public class SimulacaoInvestimento extends PanacheEntity {

    @NotNull
    @Column(name = "cliente_id")
    public Long clienteId;

    @NotNull
    @Positive
    public BigDecimal valor;

    @NotNull
    @Column(name = "prazo_meses")
    public Integer prazoMeses;

    @NotNull
    @Column(name = "tipo_produto")
    public String tipoProduto;

    @Column(name = "produto_validado")
    public String produtoValidado;

    @Column(name = "valor_final")
    public BigDecimal valorFinal;

    @Column(name = "rentabilidade_efetiva")
    public BigDecimal rentabilidadeEfetiva;

    @Column(name = "data_simulacao")
    public LocalDateTime dataSimulacao;

    @PrePersist
    public void prePersist() {
        this.dataSimulacao = LocalDateTime.now();
    }
}
