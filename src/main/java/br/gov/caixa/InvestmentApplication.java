package br.gov.caixa;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@QuarkusMain
@ApplicationPath("/api")
public class InvestmentApplication extends Application {

    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
