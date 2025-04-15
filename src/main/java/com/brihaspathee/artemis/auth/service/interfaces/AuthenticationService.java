package com.brihaspathee.artemis.auth.service.interfaces;

import com.brihaspathee.artemis.dto.auth.AuthorizationRequest;
import com.brihaspathee.artemis.dto.auth.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:44
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AuthenticationService {

    /**
     * Validates whether the user has access to a specific resource based on their details and the authorization request.
     *
     * @param userDetails the details of the user requesting access
     * @param authorizationRequest the detailed request containing information about the resource and access level
     * @return a UserDto containing the validated user information and their access permissions
     */
    UserDto validateResourceAccess(UserDetails userDetails, AuthorizationRequest authorizationRequest);
}
