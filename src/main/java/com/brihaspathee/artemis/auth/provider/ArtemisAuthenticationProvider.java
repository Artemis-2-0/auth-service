package com.brihaspathee.artemis.auth.provider;

import com.brihaspathee.artemis.auth.ArtemisAuthenticationToken;
import com.brihaspathee.artemis.auth.ArtemisUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, April 2025
 * Time: 21:53
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.provider
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
//@Component
@RequiredArgsConstructor
public class ArtemisAuthenticationProvider implements AuthenticationProvider {

    /**
     * Provides access to the ArtemisUserDetailsService, which is responsible
     * for loading user details from the database and constructing the UserDetails
     * object required within the Spring Security framework.
     * Used as a dependency in the ArtemisAuthenticationProvider for authentication purposes.
     */
    private final ArtemisUserDetailsService artemisUserDetailsService;

    /**
     * Authenticates the provided {@code Authentication} token by validating
     * its credentials against the user information stored in the system.
     *
     * @param authentication the authentication request object, containing the principal,
     *                        credentials, and additional metadata required for authentication
     * @return a fully authenticated {@code Authentication} object containing details
     *         about the authenticated user and their granted authorities
     * @throws AuthenticationException if the authentication process fails due to
     *                                  invalid credentials or other reasons
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Authenticating user {}", authentication.getName());
        ArtemisAuthenticationToken artemisAuthenticationToken = authentication instanceof ArtemisAuthenticationToken ? (ArtemisAuthenticationToken) authentication : null;
        if(artemisAuthenticationToken == null){
            throw new AuthenticationException("Invalid authentication token") {};
        }
        String username = artemisAuthenticationToken.getName();
        String password = authentication.getCredentials().toString();
        String accountType = artemisAuthenticationToken.getAccountType();

        UserDetails userDetails = artemisUserDetailsService.loadUserByUsernameAndAccountType(username, accountType);

        if(!new BCryptPasswordEncoder().matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid credentials");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                password,
                userDetails.getAuthorities());
    }

    /**
     * Determines if the specified authentication class is supported by this provider.
     *
     * @param authentication the class of the {@code Authentication} object to check
     * @return {@code true} if the specified class is assignable to ArtemisAuthenticationToken, {@code false} otherwise
     */
    @Override
    public boolean supports(Class<?> authentication) {

        return ArtemisAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
