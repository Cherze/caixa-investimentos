package br.gov.caixa.resource;

import br.gov.caixa.dto.ProdutoRecomendadoResponse;
import br.gov.caixa.model.ProdutoInvestimento;
import br.gov.caixa.service.ProdutoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.math.BigDecimal;
import java.util.List;

@Path("/api/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityRequirement(name = "Keycloak")
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Path("/recomendados/{perfil}")
    @RolesAllowed("user")
    @Operation(summary = "Produtos recomendados", description = "Retorna produtos de investimento recomendados para o perfil")
    public Response obterProdutosRecomendados(@PathParam("perfil") String perfil) {
        try {
            List<ProdutoRecomendadoResponse> produtos = produtoService.obterProdutosRecomendados(perfil);
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao buscar produtos recomendados: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @RolesAllowed("user")
    @Operation(summary = "Listar todos os produtos", description = "Retorna todos os produtos de investimento disponíveis")
    public Response obterTodosProdutos() {
        try {
            List<ProdutoRecomendadoResponse> produtos = produtoService.obterTodosProdutos();
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao buscar produtos: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/tipo/{tipo}")
    @RolesAllowed("user")
    @Operation(summary = "Produtos por tipo", description = "Retorna produtos de investimento filtrados por tipo")
    public Response obterProdutosPorTipo(@PathParam("tipo") String tipo) {
        try {
            List<ProdutoRecomendadoResponse> produtos = produtoService.obterProdutosPorTipo(tipo);
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao buscar produtos por tipo: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/risco/{risco}")
    @RolesAllowed("user")
    @Operation(summary = "Produtos por risco", description = "Retorna produtos de investimento filtrados por nível de risco")
    public Response obterProdutosPorRisco(@PathParam("risco") String risco) {
        try {
            List<ProdutoRecomendadoResponse> produtos = produtoService.obterProdutosPorRisco(risco);
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao buscar produtos por risco: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/rentabilidade-minima/{rentabilidade}")
    @RolesAllowed("user")
    @Operation(summary = "Produtos por rentabilidade mínima", description = "Retorna produtos com rentabilidade igual ou superior ao valor informado")
    public Response obterProdutosPorRentabilidadeMinima(@PathParam("rentabilidade") BigDecimal rentabilidade) {
        try {
            List<ProdutoRecomendadoResponse> produtos = produtoService.obterProdutosPorRentabilidadeMinima(rentabilidade);
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao buscar produtos por rentabilidade: " + e.getMessage()))
                    .build();
        }
    }

    @POST
    @RolesAllowed("admin")
    @Operation(summary = "Criar produto", description = "Cria um novo produto de investimento")
    public Response criarProduto(@Valid ProdutoInvestimento produto) {
        try {
            ProdutoInvestimento produtoCriado = produtoService.criarProduto(produto);
            return Response.status(Response.Status.CREATED).entity(produtoCriado).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao criar produto: " + e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    @Operation(summary = "Atualizar produto", description = "Atualiza um produto de investimento existente")
    public Response atualizarProduto(@PathParam("id") Long id, @Valid ProdutoInvestimento produto) {
        try {
            ProdutoInvestimento produtoAtualizado = produtoService.atualizarProduto(id, produto);
            if (produtoAtualizado != null) {
                return Response.ok(produtoAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Produto não encontrado"))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao atualizar produto: " + e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    @Operation(summary = "Deletar produto", description = "Remove um produto de investimento")
    public Response deletarProduto(@PathParam("id") Long id) {
        try {
            boolean deletado = produtoService.deletarProduto(id);
            if (deletado) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Produto não encontrado"))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao deletar produto: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("user")
    @Operation(summary = "Obter produto por ID", description = "Retorna um produto específico pelo ID")
    public Response obterProdutoPorId(@PathParam("id") Long id) {
        try {
            ProdutoInvestimento produto = produtoService.obterProdutoPorId(id);
            if (produto != null) {
                return Response.ok(produto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Produto não encontrado"))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro ao buscar produto: " + e.getMessage()))
                    .build();
        }
    }

    public static class ErrorResponse {
        public String message;
        public String timestamp;

        public ErrorResponse(String message) {
            this.message = message;
            this.timestamp = java.time.LocalDateTime.now().toString();
        }
    }
}
