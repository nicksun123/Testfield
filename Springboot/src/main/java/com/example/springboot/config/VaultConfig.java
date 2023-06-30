package com.example.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
@ConfigurationProperties("app.config.auth")
public class VaultConfig extends AbstractVaultConfiguration {
    @Value("${vault.token}")
    private String token;

    @Value("${vault.host}")
    private String host;
    @Value("${vault.schema}")
    private String schema;
    @Value("${vault.port}")
    private Integer port;

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication(token);
    }

    @Override
    public VaultEndpoint vaultEndpoint() {
        VaultEndpoint vaultEndpoint = new VaultEndpoint();
        vaultEndpoint.setHost(host);
        vaultEndpoint.setPort(port);
        vaultEndpoint.setScheme(schema);
        return vaultEndpoint;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
