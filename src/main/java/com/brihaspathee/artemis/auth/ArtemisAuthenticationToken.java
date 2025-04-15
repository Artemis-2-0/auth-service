package com.brihaspathee.artemis.auth;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, April 2025
 * Time: 21:23
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Slf4j
public class ArtemisAuthenticationToken extends UsernamePasswordAuthenticationToken {

    /**
     * Represents the type of the account associated with this authentication token.
     * It is used to differentiate between different user account types such as
     * regular user accounts and service user accounts.
     * This variable provides context during authentication and authorization
     * processes to determine the appropriate logic for handling user accounts.
     */
    private final String accountType;

    /**
     * Constructs an {@code ArtemisAuthenticationToken} instance with the provided principal,
     * credentials, and account type. This token represents the authentication request and
     * includes additional metadata about the type of account being authenticated.
     *
     * This constructor is used prior to the authentication of the user
     *
     * @param principal the identity of the principal (e.g., a username or email)
     * @param credentials the credentials that prove the principal's identity (e.g., a password)
     * @param accountType the type of the account associated with this authentication token
     */
    public ArtemisAuthenticationToken(String principal, String credentials, String accountType) {
        super(principal, credentials);
        this.accountType = accountType;
    }

    /**
     * Constructs an {@code ArtemisAuthenticationToken} with the provided user details, credentials,
     * account type, and authorities. This token represents the authentication of a user after
     * successful verification.
     *
     * @param userDetails the authenticated user's details, including identity and other related metadata
     * @param credentials the credentials that prove the user's identity (e.g., a password)
     * @param accountType the type of the account associated with this authentication token
     * @param authorities the authorities granted to the user, which specify their permissions and roles
     */
    public ArtemisAuthenticationToken(UserDetails userDetails,
                                      String credentials,
                                      String accountType,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(userDetails, credentials, authorities);
        this.accountType = accountType;

    }

}
