package com.brihaspathee.artemis.config;

import com.brihaspathee.artemis.auth.ArtemisAuthenticationEntryPoint;
import com.brihaspathee.artemis.auth.ArtemisUserDetailsService;
import com.brihaspathee.artemis.auth.filter.ArtemisAuthenticationFilter;
import com.brihaspathee.artemis.auth.filter.LoginAuthenticationFilter;
import com.brihaspathee.artemis.auth.provider.ArtemisAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 5:03 PM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.config
 * To change this template use File | Settings | File and Code Template
 */
@Configuration
@EnableWebSecurity
public class ArtemisSecurityConfig {


    /**
     * Represents the authentication filter used for intercepting and validating
     * authentication requests in the Artemis application. This component is configured
     * to process incoming HTTP requests, extract and validate JSON Web Tokens (JWT),
     * and establish authenticated user sessions in the Spring Security context.
     *
     * It relies on the ArtemisUserDetailsService for loading user details and the JwtService
     * for handling token generation, validation, and extraction of relevant token details.
     * The filter ensures that requests with valid tokens are associated with the appropriate
     * user credentials and authorities in the application's security context.
     */
    private final ArtemisAuthenticationFilter artemisAuthenticationFilter;

    /**
     * A reference to the {@link ArtemisAuthenticationProvider} instance, which serves as
     * a custom authentication provider within the Spring Security framework. This provider
     * is responsible for performing user authentication by integrating with the
     * {@link ArtemisUserDetailsService} to load user-specific data and validating credentials.
     *
     * This field is primarily used in the security configuration to delegate authentication
     * tasks and enforce security policies for the application.
     */
    private final ArtemisAuthenticationProvider artemisAuthenticationProvider;

    /**
     * Constructs an instance of ArtemisSecurityConfig.
     *
     * @param artemisAuthenticationFilter the custom authentication filter to handle request filtering
     *                                     and processing of authentication tokens for incoming requests.
     * @param artemisAuthenticationProvider the authentication provider used to validate authentication
     *                                       requests and load user-specific details during authentication.
     */
    public ArtemisSecurityConfig(ArtemisAuthenticationFilter artemisAuthenticationFilter,
                                 ArtemisAuthenticationProvider artemisAuthenticationProvider) {
        this.artemisAuthenticationFilter = artemisAuthenticationFilter;
        this.artemisAuthenticationProvider = artemisAuthenticationProvider;
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",

            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/h2-console/**",
            "/host",
            "/swagger-ui.html",
            "/v3/api-docs.yaml",
            "/api/v1/artemis/auth/public/**",
            // other public endpoints of your API may be appended to this array
//            "/artemis/jwt/authenticate",
//            "/api/v1/artemis/welcome",
//            "/api/v1/artemis/login",
//            "/api/v1/artemis/register",
    };

    /**
     * Configures the security filter chain for the application. This method sets up
     * various security configurations, including session management, CSRF, CORS, request
     * authorization, and custom authentication filters. It ensures that only authenticated
     * requests can access protected resources while allowing certain whitelist endpoints to
     * be publicly accessible.
     *
     * @param http the {@link HttpSecurity} instance used to configure web-based security
     *             for specific HTTP requests, providing methods for enabling or disabling
     *             features like CSRF, CORS, session management, and request authorization
     * @return a fully configured {@link SecurityFilterChain} instance that defines the
     *         application's security rules and filters
     * @throws Exception if any error occurs during the configuration of the security filter chain
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            AuthenticationManager authenticationManager) throws Exception {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(authenticationManager);
        loginAuthenticationFilter.setFilterProcessesUrl("/api/v1/artemis/auth/public/authenticate");
        http.sessionManagement(sesssion -> sesssion.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated());
        http.addFilterAt(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(artemisAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(exception ->
                exception.authenticationEntryPoint(new ArtemisAuthenticationEntryPoint()));
        return http.build();
    }

//    /**
//     * Provides a bean of type {@link PasswordEncoder} for encoding passwords.
//     * This method returns an instance of {@link BCryptPasswordEncoder}, which
//     * is a commonly used implementation for securely hashing passwords.
//     *
//     * @return an instance of {@link PasswordEncoder} configured with {@link BCryptPasswordEncoder}
//     */
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /**
     * Configures and provides an instance of {@link AuthenticationManager} for the application's
     * security configuration. This method sets up the {@link AuthenticationManagerBuilder} with a
     * custom authentication provider and builds the {@link AuthenticationManager}.
     *
     * @param http the {@link HttpSecurity} instance used for retrieving and configuring
     *             shared security objects, particularly the {@link AuthenticationManagerBuilder}.
     * @return a fully configured {@link AuthenticationManager} instance used for authentication
     *         within the security framework.
     * @throws Exception if an error occurs during the setup or building of the {@link AuthenticationManager}.
     */
    @Bean
    protected AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(artemisAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }
}
