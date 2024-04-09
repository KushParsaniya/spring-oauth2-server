package dev.kush.oauth2authserver.mappers;

import dev.kush.oauth2authserver.models.client.AuthenticationMethod;
import dev.kush.oauth2authserver.models.client.Client;
import dev.kush.oauth2authserver.models.client.GrantType;
import dev.kush.oauth2authserver.models.client.RedirectUrl;
import dev.kush.oauth2authserver.models.client.Scope;
import dev.kush.oauth2authserver.models.client.TokenSetting;
import dev.kush.oauth2authserver.models.client.TokenType;
import dev.kush.oauth2authserver.repos.client.AuthenticationMethodRepository;
import dev.kush.oauth2authserver.repos.client.GrantTypeRepository;
import dev.kush.oauth2authserver.repos.client.ScopeRepository;
import dev.kush.oauth2authserver.repos.client.TokenTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final GrantTypeRepository grantTypeRepository;
    private final ScopeRepository scopeRepository;
    private final AuthenticationMethodRepository authenticationMethodRepository;
    private final TokenTypeRepository tokenTypeRepository;

    // RegisteredClient -> Client
    public Client mapRegisteredClientToClient(RegisteredClient registeredClient) {
        Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setClientSecret(registeredClient.getClientSecret());

        mapScopes(client, registeredClient);
        mapGrantTypes(client, registeredClient);
        mapAuthenticationMethods(client, registeredClient);
        mapTokenSetting(client, registeredClient);
        mapRedirectUrls(client, registeredClient);

        return client;
    }

    private void mapTokenSetting(Client client, RegisteredClient registeredClient) {
        final TokenType tokenType = findOrCreateTokenType(registeredClient);

        TokenSetting tokenSetting = new TokenSetting(
                (int) registeredClient.getTokenSettings().getAccessTokenTimeToLive().toHours(),
                tokenType,
                client
        );
        tokenType.setTokenSettings(List.of(tokenSetting));

        client.setTokenSetting(tokenSetting);
    }

    private TokenType findOrCreateTokenType(RegisteredClient registeredClient) {
        return tokenTypeRepository.findByTokenType(registeredClient.getTokenSettings().getAccessTokenFormat().getValue())
                .orElseGet(() -> new TokenType(registeredClient.getTokenSettings().getAccessTokenFormat().getValue()));
    }

    private void mapRedirectUrls(Client client, RegisteredClient registeredClient) {
        client.setRedirectUrls(registeredClient.getRedirectUris().stream()
                .map(r -> new RedirectUrl(r, client))
                .toList());
    }


    private void mapGrantTypes(Client client, RegisteredClient registeredClient) {
        client.setGrantTypes(registeredClient.getAuthorizationGrantTypes().stream()
                .map(g -> findOrCreateGrantType(g.getValue(), client))
                .toList());
    }

    private GrantType findOrCreateGrantType(String grantTypeValue, Client client) {
        GrantType grantType = grantTypeRepository.findByGrantType(grantTypeValue)
                .orElseGet(() -> new GrantType(grantTypeValue, List.of(client)));
        grantType.getClients().add(client);
        return grantType;
    }

    private void mapScopes(Client client, RegisteredClient registeredClient) {
        client.setScopes(registeredClient.getScopes().stream()
                .map(s -> findOrCreateScope(s, client))
                .toList());
    }

    private Scope findOrCreateScope(String scopeValue, Client client) {
        Scope scope = scopeRepository.findByScope(scopeValue)
                .orElseGet(() -> new Scope(scopeValue, List.of(client)));
        scope.getClients().add(client);
        return scope;
    }

    private void mapAuthenticationMethods(Client client, RegisteredClient registeredClient) {
        client.setAuthenticationMethods(registeredClient.getClientAuthenticationMethods().stream()
                .map(a -> findOrCreateAuthenticationMethod(a.getValue(), client))
                .toList());
    }

    private AuthenticationMethod findOrCreateAuthenticationMethod(String methodValue, Client client) {
        AuthenticationMethod method = authenticationMethodRepository.findByAuthenticationMethod(methodValue)
                .orElseGet(() -> new AuthenticationMethod(methodValue, List.of(client)));
        method.getClients().add(client);
        return method;
    }

    // Client -> RegisteredClient
    public RegisteredClient mapClientToRegisteredClient(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientAuthenticationMethods(clientAuthenticationMethods(client.getAuthenticationMethods()))
                .authorizationGrantTypes(authorizationGrantTypes(client.getGrantTypes()))
                .scopes(scopes(client.getScopes()))
                .redirectUris(redirectUris(client.getRedirectUrls()))
                .tokenSettings(
                        getTokenSettings(client)
                )
                .build();
    }

    private static TokenSettings getTokenSettings(Client client) {
        return TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofHours(client.getTokenSetting().getAccessTokenTtl()))
                .accessTokenFormat(new OAuth2TokenFormat(client.getTokenSetting().getTokenType().getTokenType()))
                .build();
    }

    private static Consumer<Set<ClientAuthenticationMethod>> clientAuthenticationMethods(List<AuthenticationMethod> authenticationMethods) {
        return s -> {
            for (AuthenticationMethod a : authenticationMethods) {
                s.add(new ClientAuthenticationMethod(a.getAuthenticationMethod()));
            }
        };
    }


    private static Consumer<Set<AuthorizationGrantType>> authorizationGrantTypes(List<GrantType> grantTypes) {
        return s -> {
            for (GrantType g : grantTypes) {
                s.add(new AuthorizationGrantType(g.getGrantType()));
            }
        };
    }

    private static Consumer<Set<String>> scopes(List<Scope> scopes) {
        return s -> {
            for (Scope sc : scopes) {
                s.add(sc.getScope());
            }
        };
    }

    private static Consumer<Set<String>> redirectUris(List<RedirectUrl> redirectUrls) {
        return s -> {
            for (RedirectUrl r : redirectUrls) {
                s.add(r.getUrl());
            }
        };
    }
}