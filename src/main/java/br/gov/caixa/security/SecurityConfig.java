package br.gov.caixa.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

@ApplicationScoped
public class SecurityConfig {

    @Provider
    public static class AuthExceptionMapper implements ExceptionMapper<ClientWebApplicationException> {

        @Override
        public Response toResponse(ClientWebApplicationException exception) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Falha na autenticação"))
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
