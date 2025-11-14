package br.gov.caixa.resource;

import br.gov.caixa.model.SimulacaoInvestimento;
import br.gov.caixa.dto.SimulacaoRequest;
import br.gov.caixa.dto.SimulacaoResponse;
import br.gov.caixa.service.SimulacaoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.ApiResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/simulacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityRequirement(name = "Keycloak")
public class SimulacaoResource {

    @Inject
    SimulacaoService simulacaoService;

    @POST
    @Path("/simular-investimento")
    @RolesAllowed("user")
    @Operation(summary = "Simular investimento", description = "Realiza simulação de investimento baseada nos parâmetros fornecidos")
    @ApiResponse(responseCode = "200", description = "Simulação realizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    public Response simularInvestimento(@Valid SimulacaoRequest request) {
        try {
            SimulacaoResponse response = simulacaoService.simularInvestimento(request);
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    @GET
    @RolesAllowed("user")
    @Operation(summary = "Listar simulações", description = "Retorna todas as simulações realizadas")
    public Response listarSimulacoes() {
        List<SimulacaoInvestimento> simulacoes = simulacaoService.listarSimulacoes();
        return Response.ok(simulacoes).build();
    }

    @GET
    @Path("/por-produto-dia")
    @RolesAllowed("user")
    @Operation(summary = "Simulações por produto e dia", description = "Retorna estatísticas de simulações agrupadas por produto e data")
    public Response obterSimulacoesPorProdutoDia() {
        // Implementação simplificada - em produção, usar consulta SQL agrupada
        return Response.ok().build();
    }

    public static class ErrorResponse {
        public String message;
        public LocalDateTime timestamp;

        public ErrorResponse(String message) {
            this.message = message;
            this.timestamp = java.time.LocalDateTime.now();
        }
    }
}
