package com.brihaspathee.artemis.web.controller.impl;

import com.brihaspathee.artemis.auth.service.interfaces.AuthenticationService;
import com.brihaspathee.artemis.dto.auth.AuthorizationRequest;
import com.brihaspathee.artemis.dto.auth.UserDto;
import com.brihaspathee.artemis.dto.resource.ResourceDto;
import com.brihaspathee.artemis.web.controller.interfaces.ResourceManagementAPI;
import com.brihaspathee.artemis.web.response.ArtemisAPIResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 14:48
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.web.controller.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ResourceManagementAPIImpl implements ResourceManagementAPI {

    /**
     * Provides authentication and authorization functionalities to validate
     * user access and permissions for resources. This service is utilized
     * within the ResourceManagementAPI implementation to handle authentication
     * operations.
     */
    private final AuthenticationService authenticationService;

    /**
     * Validates if the authenticated user has the necessary authorities to access a specific resource.
     * The method checks the user's details and authorities against the required access permissions for the resource.
     * If the validation is successful, a response containing the user's details is returned.
     *
     * @param userDetails the authenticated user's details, including their authorities
     * @param request the authorization request containing information about the resource and required access permissions
     * @return a ResponseEntity containing an ArtemisAPIResponse with a UserDto object and additional details such as status and message
     */
    @Override
    public ResponseEntity<ArtemisAPIResponse<UserDto>> validateResource(@AuthenticationPrincipal UserDetails userDetails,
                                                                        @Valid @RequestBody AuthorizationRequest request) {
        /*
            This is a secured endpoint, so if the control reaches to the controller, the user is already authenticated
            and the security context holder will have the authenticated user details
            This user details is being fetched from the database in the SapphireUserDetails service
            At that point all the authorities associated with the user is also fetched.
            So, with the user details and their authorities already available at the time
            the control reaches here, the only thing needed to be done is to get the
            authorities needed for the resource and compare it with the authorities of the user
            and if the user has the authority then return the username. This logic of comparing the
            user's authorities with the authority need to access the resource is done in the authentication service
         */
        log.info("Validating resource access for user: {}", userDetails.getUsername());
        UserDto userDto = authenticationService.validateResourceAccess(userDetails, request);
        ArtemisAPIResponse<UserDto> apiResponse = ArtemisAPIResponse.<UserDto>builder()
                .response(userDto)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .reason("Token Validation Success")
                .message("Token Successfully validated")
                .developerMessage("Token Successfully validated")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
