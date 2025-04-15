package com.brihaspathee.artemis.auth.filter;

import com.brihaspathee.artemis.auth.ArtemisAuthenticationToken;
import com.brihaspathee.artemis.auth.document.ServiceUser;
import com.brihaspathee.artemis.auth.document.User;
import com.brihaspathee.artemis.auth.service.JwtService;
import com.brihaspathee.artemis.dto.auth.AuthenticationRequest;
import com.brihaspathee.artemis.dto.auth.AuthenticationResponse;
import com.brihaspathee.artemis.web.response.ArtemisAPIResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, April 2025
 * Time: 21:47
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.filter
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * A service used for handling operations related to JSON Web Tokens (JWTs).
     * It provides functionality for generating, validating, and decoding JWTs.
     * This service facilitates secure authentication by allowing token-based
     * authorization workflows.
     */
    private final JwtService jwtService = new JwtService();

    /**
     * Constructs an instance of LoginAuthenticationFilter.
     * This filter is responsible for handling authentication requests
     * by processing username and password authentication.
     *
     * @param authenticationManager the authentication manager used to process
     *                               authentication requests and manage security context
     */
    public LoginAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    /**
     * Processes an authentication attempt by extracting user credentials and account metadata
     * from the incoming HTTP request, creating an authentication token, and delegating the
     * authentication process to the provided authentication manager.
     *
     * @param request the incoming HTTP request containing authentication details, such as
     *                username, password, and optionally, account type in the headers
     * @param response the HTTP response for the current request, used to send error or
     *                 success messages if needed
     * @return an {@code Authentication} object representing the successfully authenticated principal
     *         and their credentials, or throws an exception if authentication fails
     * @throws AuthenticationException if the authentication process encounters an error, such as
     *                                 invalid credentials, malformed request, or unforeseen exceptions
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        try{

            // Read request body
            AuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
            String username = authenticationRequest.getUsername();
            log.info("Attempting authentication for user {}", username);
            String password = authenticationRequest.getPassword();
            String accountType = authenticationRequest.getAccountType();

            ArtemisAuthenticationToken authenticationToken = new ArtemisAuthenticationToken(username, password, accountType);
            setDetails(request, authenticationToken);
            return getAuthenticationManager().authenticate(authenticationToken);
        }catch(IOException e){
            throw new AuthenticationServiceException("Failed to read request body");
        }
    }

    /**
     * Handles successful authentication events by generating an access token for the authenticated
     * user. It checks the type of the authenticated principal and generates a JSON Web Token (JWT)
     * accordingly, setting it in the response for further use.
     *
     * @param request the HTTP servlet request containing the authentication details and additional context
     * @param response the HTTP servlet response where the generated access token may be included
     * @param chain the filter chain that allows further filter processing
     * @param authResult the authentication result containing details of the authenticated principal
     * @throws IOException if an input-output error occurs during the generation or handling of the response
     * @throws ServletException if an error occurs during the servlet processing
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        Object principal = authResult.getPrincipal();
        if(principal instanceof User user){
            final String accessToken = jwtService.generateToken(user, "USER-ACCOUNT");
            response.setHeader("Authorization", "Bearer " + accessToken);
            returnAuthenticationResponse(accessToken, response);
        }else if (principal instanceof ServiceUser serviceUser){
             final String accessToken = jwtService.generateToken(serviceUser, "SERVICE-ACCOUNT");
             response.setHeader("Authorization", "Bearer " + accessToken);
             returnAuthenticationResponse(accessToken, response);
        }
        super.successfulAuthentication(request, response, chain, authResult);
    }

    /**
     * Handles unsuccessful authentication attempts by constructing a structured error
     * response and sending it back to the client. This method customizes the default
     * unsuccessful authentication behavior to provide detailed feedback in JSON format
     * regarding the failure reason.
     *
     * @param request the incoming HTTP request containing the user's authentication attempt.
     *                This object holds the request details like headers and parameters.
     * @param response the HTTP response that will be sent back to the client. The response
     *                 is customized to include the error details in JSON format.
     * @param failed the exception that represents the cause of the authentication failure.
     *               It encapsulates error details that occurred during the authentication process.
     * @throws IOException if an input or output exception occurs during response handling.
     * @throws ServletException if an error occurs during the processing of the response.
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        // Build structured error response
        ArtemisAPIResponse<Object> apiResponse = ArtemisAPIResponse.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .reason("Authentication Failed")
                .message("Invalid username, password, or account type")
                .developerMessage(failed.getMessage()) // this usually gives you the exception message
                .timestamp(LocalDateTime.now())
                .build();

        // Set response headers and write the JSON
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); // for LocalDateTime
        mapper.writeValue(response.getOutputStream(), apiResponse);
    }

    /**
     * Sends a JSON response representing the authentication result back to the client.
     * The response includes a JWT token on successful authentication along with additional
     * metadata like status, reason, and timestamp.
     *
     * @param jwt the JSON Web Token generated for the authenticated user
     * @param response the HTTP servlet response object used to send the response to the client
     * @throws IOException if an input or output exception occurs during writing the response
     */
    private void returnAuthenticationResponse(String jwt, HttpServletResponse response) throws IOException {
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token( jwt)
                .build();
        ArtemisAPIResponse<AuthenticationResponse> apiResponse = ArtemisAPIResponse.<AuthenticationResponse>builder()
                .response(authenticationResponse)
                .status(HttpStatus.OK)
                .reason("Authentication Success")
                .message("User Successfully authenticated")
                .developerMessage("User Successfully authenticated")
                .timestamp(LocalDateTime.now())
                .build();

        // Set headers and write the response
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); // for LocalDateTime support

        mapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
