package br.gov.caixa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfis_risco")
public class PerfilRisco extends PanacheEntity {

    @NotNull
    @Column(name = "cliente_id", unique = true)
    public Long clienteId;

    @NotNull
    public String perfil;

    @NotNull
    public Integer pontuacao;

    public String descricao;

    @Column(name = "volume_investimentos")
    public BigDecimal volumeInvestimentos;

    @Column(name = "frequencia_movimentacoes")
    public Integer frequenciaMovimentacoes;

    @Column(name = "preferencia_liquidez")
    public Boolean preferenciaLiquidez;

    @Column(name = "data_atualizacao")
    public LocalDateTime dataAtualizacao;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
