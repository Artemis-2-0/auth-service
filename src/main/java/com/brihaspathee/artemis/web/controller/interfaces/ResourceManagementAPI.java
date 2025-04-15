package com.brihaspathee.artemis.web.controller.interfaces;

import com.brihaspathee.artemis.dto.auth.AuthorizationRequest;
import com.brihaspathee.artemis.dto.auth.UserDto;
import com.brihaspathee.artemis.dto.resource.ResourceDto;
import com.brihaspathee.artemis.web.response.ArtemisAPIResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 14:21
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.web.controller.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/artemis/auth/secured")
public interface ResourceManagementAPI {

    /**
     * Validates a given resource based on the authorization request and user details.
     *
     * @param userDetails the authenticated user details used for validation
     * @param request the authorization request containing details to authorize the resource
     * @return ResponseEntity containing an ArtemisAPIResponse with the validated UserDto
     */
    @PostMapping("/resource/validate")
    ResponseEntity<ArtemisAPIResponse<UserDto>> validateResource(
            @AuthenticationPrincipal UserDetails userDetails, @Valid
                                                                     @RequestBody
                                                                     AuthorizationRequest request);
}
