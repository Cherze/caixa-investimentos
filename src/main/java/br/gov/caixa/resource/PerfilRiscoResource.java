package br.gov.caixa.resource;

import br.gov.caixa.dto.PerfilRiscoResponse;
import br.gov.caixa.service.PerfilRiscoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.Optional;

@Path("/api/perfil-risco")
@Produces(MediaType.APPLICATION_JSON)
@SecurityRequirement(name = "Keycloak")
public class PerfilRiscoResource {

    @Inject
    PerfilRiscoService perfilRiscoService;

    @GET
    @Path("/{clienteId}")
    @RolesAllowed("user")
    @Operation(summary = "Obter perfil de risco", description = "Retorna o perfil de risco calculado para o cliente")
    public Response obterPerfilRisco(@PathParam("clienteId") Long clienteId) {
        Optional<PerfilRiscoResponse> perfil = perfilRiscoService.obterPerfilRisco(clienteId);

        if (perfil.isPresent()) {
            return Response.ok(perfil.get()).build();
        } else {
            // Se não existe, calcula um novo perfil
            PerfilRiscoResponse novoPerfil = perfilRiscoService.calcularPerfilRisco(clienteId);
            return Response.ok(novoPerfil).build();
        }
    }
}
