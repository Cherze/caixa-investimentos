package br.gov.caixa.service;

import br.gov.caixa.dto.TelemetriaResponse;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class TelemetriaService {

    private final Random random = new Random();

    public TelemetriaResponse obterDadosTelemetria() {
        List<TelemetriaResponse.ServicoMetrica> servicos = Arrays.asList(
                new TelemetriaResponse.ServicoMetrica("simular-investimento", 120L, 250.0),
                new TelemetriaResponse.ServicoMetrica("perfil-risco", 80L, 180.0),
                new TelemetriaResponse.ServicoMetrica("produtos-recomendados", 95L, 120.0),
                new TelemetriaResponse.ServicoMetrica("historico-investimentos", 60L, 150.0)
        );

        TelemetriaResponse.Periodo periodo = new TelemetriaResponse.Periodo(
                LocalDate.now().minusDays(30),
                LocalDate.now()
        );

        TelemetriaResponse response = new TelemetriaResponse();
        response.servicos = servicos;
        response.periodo = periodo;

        return response;
    }
}
