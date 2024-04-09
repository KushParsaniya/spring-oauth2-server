# OAuth2/ OpenID
## OAuth2 Server Implemantation Using Latest Spring Security
### Overview
This project implements an OAuth2 authorization server using Spring Boot 3.2.0, providing secure authentication and authorization mechanisms for client applications. It stores OAuth2 client details and user credentials in a PostgreSQL database.

### Endpoints

1. **Authorization Endpoint:**
   - URL: `http://localhost:8080/oauth2/authorize`
   - Method: GET
   - Parameters: `response_type`, `client_id`, `scope`, `redirect_uri`, `code_challenge`, `code_challenge_method`
   - Description: Initiates the OAuth2 authorization process.

2. **OpenID Configuration Endpoint:**
   - URL: `http://localhost:8080/.well-known/openid-configuration`
   - Method: GET
   - Description: Provides metadata about the authorization server.

3. **Token Endpoint:**
   - URL: `http://localhost:8080/oauth2/token`
   - Method: POST
   - Parameters: `client_id`, `grant_type`, `redirect_uri`, `code`, `code_verifier`
   - Description: Exchanges authorization code for an access token.

4. **Token Introspection Endpoint:**
   - URL: `http://localhost:8080/oauth2/introspect`
   - Method: POST
   - Parameters: `token`
   - Description: Verifies the validity and scope of an access token.

### Usage
1. Obtain authorization by visiting the authorization URL with required parameters.
2. Exchange authorization code for an access token using the token endpoint.
3. Introspect the access token for validity and scope using the introspection endpoint.

### Note
Ensure proper authentication and authorization mechanisms are in place to secure endpoints and sensitive data.

