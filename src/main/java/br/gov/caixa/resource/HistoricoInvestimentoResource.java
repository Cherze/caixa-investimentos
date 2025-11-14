package br.gov.caixa.resource;

import br.gov.caixa.model.HistoricoInvestimento;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.List;

@Path("/api/investimentos")
@Produces(MediaType.APPLICATION_JSON)
@SecurityRequirement(name = "Keycloak")
public class HistoricoInvestimentoResource {

    @GET
    @Path("/{clienteId}")
    @RolesAllowed("user")
    @Operation(summary = "Histórico de investimentos", description = "Retorna o histórico de investimentos do cliente")
    public Response obterHistoricoInvestimentos(@PathParam("clienteId") Long clienteId) {
        List<HistoricoInvestimento> historico = HistoricoInvestimento
                .find("clienteId", clienteId)
                .list();

        return Response.ok(historico).build();
    }
}
