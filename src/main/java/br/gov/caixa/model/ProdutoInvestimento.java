package br.gov.caixa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos_investimento")
public class ProdutoInvestimento extends PanacheEntity {

    @NotNull
    public String nome;

    @NotNull
    public String tipo;

    @NotNull
    public BigDecimal rentabilidade;

    @NotNull
    public String risco;

    @Column(name = "prazo_minimo_meses")
    public Integer prazoMinimoMeses;

    @Column(name = "valor_minimo")
    public BigDecimal valorMinimo;

    @Column(name = "perfil_recomendado")
    public String perfilRecomendado;

    public Boolean ativo = true;
}
