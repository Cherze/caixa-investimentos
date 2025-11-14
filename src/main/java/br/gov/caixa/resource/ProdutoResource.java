package br.gov.caixa.resource;

import br.gov.caixa.dto.ProdutoRecomendadoResponse;
import br.gov.caixa.service.ProdutoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.List;

@Path("/api/produtos")
@Produces(MediaType.APPLICATION_JSON)
@SecurityRequirement(name = "Keycloak")
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Path("/recomendados/{perfil}")
    @RolesAllowed("user")
    @Operation(summary = "Produtos recomendados", description = "Retorna produtos de investimento recomendados para o perfil")
    public Response obterProdutosRecomendados(@PathParam("perfil") String perfil) {
        List<ProdutoRecomendadoResponse> produtos = produtoService.obterProdutosRecomendados(perfil);
        return Response.ok(produtos).build();
    }

    @GET
    @RolesAllowed("user")
    @Operation(summary = "Listar todos os produtos", description = "Retorna todos os produtos de investimento disponíveis")
    public Response obterTodosProdutos() {
        List<ProdutoRecomendadoResponse> produtos = produtoService.obterTodosProdutos();
        return Response.ok(produtos).build();
    }
}
