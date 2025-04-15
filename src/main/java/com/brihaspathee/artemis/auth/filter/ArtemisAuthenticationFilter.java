package com.brihaspathee.artemis.auth.filter;

import com.brihaspathee.artemis.auth.ArtemisAuthenticationToken;
import com.brihaspathee.artemis.auth.ArtemisUserDetailsService;
import com.brihaspathee.artemis.auth.document.User;
import com.brihaspathee.artemis.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 4:11 PM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.filter
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class ArtemisAuthenticationFilter extends OncePerRequestFilter {

    /**
     * A service layer dependency used for handling user authentication and authorization.
     * Specifically, it loads user details required by Spring Security for authentication
     * processes. This service interacts with the underlying user data repository to retrieve
     * user information based on the provided username, enabling secure access control.
     */
    private final ArtemisUserDetailsService artemisUserDetailsService;

    /**
     * A service component responsible for generating, validating, and extracting data
     * from JSON Web Tokens (JWTs). This service provides methods for securely handling
     * authentication tokens, including operations like token creation, validation of token
     * expiration, extraction of username and authorities from tokens, and verifying their integrity.
     *
     * Utilizes a secret key for signing and verifying tokens, ensuring security and data integrity.
     * The expiration duration of the tokens is also configurable.
     */
    private final JwtService jwtService;

    /**
     * Constructs an instance of ArtemisAuthenticationFilter.
     *
     * @param artemisUserDetailsService the service used for loading user-specific data
     * @param jwtService the service used for handling JWT operations
     */
    public ArtemisAuthenticationFilter(ArtemisUserDetailsService artemisUserDetailsService, JwtService jwtService) {
        this.artemisUserDetailsService = artemisUserDetailsService;
        this.jwtService = jwtService;
    }

    /**
     * Processes the incoming HTTP requests to handle authentication using JWT tokens.
     * This method extracts the JWT token from the "Authorization" header, validates the token,
     * extracts user details, and sets an authentication object in the security context if valid.
     *
     * @param request the incoming HTTP request to filter. It contains the headers, body,
     *                and other details, including the "Authorization" header from which
     *                the JWT token is extracted.
     * @param response the HTTP response to be sent back to the client. It is used to send
     *                 responses or errors if any exception arises during processing.
     * @param filterChain the chain of filters where this method belongs. It is used to pass
     *                    the request and response to the next filter in the chain once processing
     *                    of the current filter is completed.
     * @throws ServletException if an error occurs during the filtering process.
     * @throws IOException if an input or output exception occurs during request or response handling.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws
            ServletException, IOException {
        log.debug("Processing authentication filter for request: {}", request.getRequestURI());
        /*
            Get the authorization header
         */
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;
        String accountType = null;
        /*
            If the authorization header is present get the Jwt token
         */
        log.debug("Authorization header: {}", authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtService.extractUsername(jwt);
            accountType = jwtService.extractAccountType(jwt);
            log.debug("Username extracted from JWT: {}", username);
        }
        /*
            If the username is extracted from the token and
            the authentication object is not yet set in the
            Security context, then an authentication object has
            to be created and set on the security context
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = artemisUserDetailsService.loadUserByUsernameAndAccountType(username, accountType);
            /*
                once the user is retrieved from the database, check if the jwt token in the
                request is valid
             */
            if(jwtService.validateToken(jwt, userDetails)){
                    /*
                        If the request is valid then perform the below steps that would have
                        been done by Spring
                        1. Get all the authorities associated with the user
                        2. Create an instance of the UsernamePasswordAuthenticationToken with the user and their authorities
                        3. Set the incoming request into the authentication token
                        4. Set the authentication token in the Security context
                     */
                List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(jwt);
                log.info("Authorities extracted from JWT: {}", authorities);
                ArtemisAuthenticationToken authToken =
                        new ArtemisAuthenticationToken(userDetails,
                                userDetails.getPassword(),
                                accountType,
                                authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else {
                log.warn("Invalid JWT token in request: {}, for user {}", jwt, userDetails.getUsername());
            }
        }

        filterChain.doFilter(request, response);

    }
}
