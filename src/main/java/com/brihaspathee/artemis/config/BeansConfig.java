package com.brihaspathee.artemis.config;

import com.brihaspathee.artemis.auth.ArtemisUserDetailsService;
import com.brihaspathee.artemis.auth.provider.ArtemisAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, April 2025
 * Time: 07:04
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.config
 * To change this template use File | Settings | File and Code Template
 */
@Configuration
public class BeansConfig {

    /**
     * Configures and provides a bean of type ArtemisAuthenticationProvider.
     * The ArtemisAuthenticationProvider is responsible for authenticating users
     * by utilizing the ArtemisUserDetailsService to load user details.
     *
     * @param userDetailsService an instance of ArtemisUserDetailsService used to fetch
     *                           user details required for authentication
     * @return an instance of ArtemisAuthenticationProvider initialized with the provided
     *         ArtemisUserDetailsService
     */
    @Bean
    public ArtemisAuthenticationProvider artemisAuthenticationProvider(ArtemisUserDetailsService userDetailsService) {
        return new ArtemisAuthenticationProvider(userDetailsService);
    }

    /**
     * Provides a bean of type {@link PasswordEncoder} for encoding passwords.
     * This method returns an instance of {@link BCryptPasswordEncoder}, which
     * is a commonly used implementation for securely hashing passwords.
     *
     * @return an instance of {@link PasswordEncoder} configured with {@link BCryptPasswordEncoder}
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
