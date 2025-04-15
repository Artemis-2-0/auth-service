package com.brihaspathee.artemis.web.controller.impl;

import com.brihaspathee.artemis.auth.ArtemisAuthenticationToken;
import com.brihaspathee.artemis.auth.document.User;
import com.brihaspathee.artemis.auth.service.JwtService;
import com.brihaspathee.artemis.dto.auth.AuthenticationRequest;
import com.brihaspathee.artemis.dto.auth.AuthenticationResponse;
import com.brihaspathee.artemis.web.controller.interfaces.AuthenticationAPI;
import com.brihaspathee.artemis.web.response.ArtemisAPIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 08, April 2025
 * Time: 14:39
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.web.controller.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationAPIImpl implements AuthenticationAPI {

    /**
     * Manages the authentication process by handling authentication requests
     * and verifying the provided credentials against configured authentication mechanisms.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Handles operations related to JWT (JSON Web Token) generation, validation,
     * and related functionalities for managing user authentication tokens.
     */
    private final JwtService jwtService;

    /**
     * Authenticates a user based on the credentials provided in the authentication request.
     *
     * @param authenticationRequest the authentication request containing user credentials
     * @return ResponseEntity containing an ArtemisAPIResponse with the authentication response
     */
//    @Override
//    public ResponseEntity<ArtemisAPIResponse<AuthenticationResponse>> authenticate(AuthenticationRequest
//                                                                                               authenticationRequest) {
//        User user = null;
//        try{
//            Authentication authentication = authenticationManager.authenticate(new ArtemisAuthenticationToken(
//                    authenticationRequest.getUsername(),
//                    authenticationRequest.getPassword(),
//                    null
//            ));
//            user = (User) authentication.getPrincipal();
//        }catch (BadCredentialsException e){
//            throw new BadCredentialsException("Invalid credentials");
//        }
//        /*
//              once the user is retrieved, generate the access token (jwt) using the details of the user
//        */
//        final String accessToken = jwtService.generateToken(user);
//        /*
//                Create the authentication response with the token and send it back to the user.
//         */
//        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
//                .token( accessToken)
//                .build();
//        ArtemisAPIResponse<AuthenticationResponse> apiResponse = ArtemisAPIResponse.<AuthenticationResponse>builder()
//                .response(authenticationResponse)
//                .status(HttpStatus.OK)
//                .reason("Authentication Success")
//                .message("User Successfully authenticated")
//                .developerMessage("User Successfully authenticated")
//                .timestamp(LocalDateTime.now())
//                .build();
//        return ResponseEntity.ok(apiResponse);
//    }
}
