package com.brihaspathee.artemis.web.controller.interfaces;

import com.brihaspathee.artemis.dto.auth.AuthenticationRequest;
import com.brihaspathee.artemis.dto.auth.AuthenticationResponse;
import com.brihaspathee.artemis.web.response.ArtemisAPIResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 08, April 2025
 * Time: 13:24
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.web.controller.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/artemis/auth/public")
public interface AuthenticationAPI {

    /**
     * Endpoint to authenticate a user with the provided credentials.
     *
     * @param authenticationRequest the authentication request containing user credentials
     * @return ResponseEntity containing an ArtemisAPIResponse with the authentication response
     */
//    @PostMapping("/authenticate")
//    ResponseEntity<ArtemisAPIResponse<AuthenticationResponse>> authenticate(@Valid
//                                                                            @RequestBody
//                                                                            AuthenticationRequest authenticationRequest);
}
