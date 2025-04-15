package com.brihaspathee.artemis.auth.service;

import com.brihaspathee.artemis.auth.document.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 3:09 PM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.service
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class JwtService {

    /**
     * Represents the secret key used for signing and verifying JWTs (JSON Web Tokens).
     * The value is sourced from the application configuration property "application.security.jwt.security-key".
     * This key is crucial for ensuring the integrity and authenticity of the generated tokens.
     */
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    /**
     * Represents the expiration duration for JWT tokens, defined in milliseconds.
     * The value is loaded from the application configuration property "application.security.jwt.expiration".
     * It determines the validity period after which the JWT token will expire.
     */
    @Value("${application.security.jwt.expiration}")
    private long expiration;

    /**
     * Generates a JSON Web Token (JWT) based on the given user's details and account type.
     *
     * @param user the user details for whom the token is being generated
     * @param accountType the account type associated with the user
     * @return a signed JWT as a String
     */
    public String generateToken(UserDetails user, String accountType) {
        log.info("Generating JWT for user: {}", user.getUsername());
        log.info("Secret Key: {}", secretKey);
        List<String> authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", authorities)
                .claim("username", user.getUsername())
                .claim("accountType", accountType)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(8640000)))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Validates a given token by checking if the username extracted from the token matches
     * the provided user's username and ensuring the token has not expired.
     *
     * @param token the JWT token to be validated
     * @param user the user details object containing the expected username
     * @return true if the token is valid (matches the user's username and is not expired), false otherwise
     */
    public boolean validateToken(String token,
                                 UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extracts the username from the provided token.
     * The username is stored as a claim within the token.
     *
     * @param token the JWT token from which the username will be extracted
     * @return the username extracted from the token
     */
    public String extractUsername(String token) {
        /*
          The username of the user is set in the subject and also set as a claim
          We are extracting the username from the claim.
          Refer to generateToken method in this class to see how the username is set
         */
        Claims claims = extractAllClaims(token);
        String username = claims.get("username", String.class);
//        String username = claims.getSubject();
        return username;
    }

    /**
     * Extracts the account type from the provided token.
     *
     * @param token a String representing the token from which the account type
     *              needs to be extracted
     * @return a String representing the account type extracted from the token
     */
    public String extractAccountType(String token) {
        Claims claims = extractAllClaims(token);
        String accountType = claims.get("accountType", String.class);
        return accountType;
    }

    /**
     * Extracts and converts the authorities from a given token into a list of {@code SimpleGrantedAuthority}.
     * The authorities are retrieved from the token's claims under the key "authorities".
     * The token must contain this key with the associated list of authorities for proper processing.
     *
     * @param token the JWT token from which the authorities are to be extracted
     * @return a list of {@code SimpleGrantedAuthority} representing the authorities contained in the token
     */
    public List<SimpleGrantedAuthority> extractAuthorities(String token) {
//        Claims claims = extractAllClaims(token);
//        List<String> authorities = claims.get("authorities", List.class);
//        return authorities.stream()
//                .map(SimpleGrantedAuthority::new)
//                .toList();
        /*
         * The authorities that are assigned to a user are set in the token as a claim with the key
         * "authorities". Refer to the method generateToken in this class to see how the authorities are set
         * in the token.
         */
        Claims claims = extractAllClaims(token);
        List<?> rawList = claims.get("authorities", List.class);
        List<String> authorities = rawList.stream().map(Object::toString).toList();
        return authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

    /**
     * Checks if the given token is expired.
     *
     * @param token the JWT token to check for expiration
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(Date.from(Instant.now()));
    }

    /**
     * Extracts all claims present in the specified JSON Web Token (JWT).
     * This method parses the token, validates it using the signing key,
     * and retrieves the claims embedded in the token's body.
     *
     * @param token the JWT string that needs to be parsed and validated
     * @return the {@link Claims} object containing all claims extracted from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generates and returns the signing key used for creating and verifying JWTs (JSON Web Tokens).
     * The method decodes a Base64-encoded secret key and uses it to create an HMAC SHA key.
     *
     * @return the signing {@link Key} derived from the secret key
     */
    private Key getSigningKey() {
        log.info("Generating signing key for JWT with secret key: {}", secretKey);
        byte[] keyBytes = Decoders.BASE64.decode("2b9e785bfd84af3bf177a19177b2a1dd27e971a8d3e7394e66f68d9701a2d6f1");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
