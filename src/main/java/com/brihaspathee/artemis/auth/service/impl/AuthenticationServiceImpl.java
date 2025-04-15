package com.brihaspathee.artemis.auth.service.impl;

import com.brihaspathee.artemis.auth.document.User;
import com.brihaspathee.artemis.auth.service.interfaces.AuthenticationService;
import com.brihaspathee.artemis.dto.auth.AuthorityDto;
import com.brihaspathee.artemis.dto.auth.AuthorizationRequest;
import com.brihaspathee.artemis.dto.auth.UserDto;
import com.brihaspathee.artemis.dto.resource.ResourceDto;
import com.brihaspathee.artemis.exception.AccessDeniedException;
import com.brihaspathee.artemis.mapper.interfaces.UserMapper;
import com.brihaspathee.artemis.services.interfaces.ResourceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:45
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * Service responsible for managing and retrieving resource details required
     * for authorization and access control.
     */
    private final ResourceManagementService resourceManagementService;

    /**
     * Mapper interface for handling the conversion between User entity and UserDto.
     * Utilized for transforming user details into a different representation
     * required by various layers of the application, such as DTOs.
     */
    private final UserMapper userMapper;

    /**
     * Validates if the user has access to a specific resource by comparing
     * user authorities with the resource's required authorities.
     *
     * @param userDetails the details of the user, including authorities, requesting access to the resource
     * @param authorizationRequest the request containing the details of the resource being accessed
     * @return a UserDto object representing the validated user details if access is allowed
     * @throws AccessDeniedException if the user does not have the required permissions to access the resource
     */
    @Override
    public UserDto validateResourceAccess(UserDetails userDetails, AuthorizationRequest authorizationRequest) {
        List<String> userAuthorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        log.info("User authorities: {}", userAuthorities);
        ResourceDto resourceDto = resourceManagementService.getResourceDetails(authorizationRequest.getResourceUri());
        List<String> resourceAuthorities = resourceDto.getAuthorities().stream().map(AuthorityDto::getPermission).toList();
        log.info("Resource authorities: {}", resourceAuthorities);
        if(!isUserAuthorized(resourceAuthorities, userAuthorities)) {
            throw new AccessDeniedException("User is not authorized to access the resource");
        }
        if(userDetails instanceof User user) {
            log.info("User: {}", user);
            log.info("User's Authorities: {}", user.getAuthorities());
            log.info("User's username: {}", user.getUsername());
//            log.info("User's serviceId: {}", user.getServiceId());
//            log.info("User's account type: {}", user.getAccountType());
            return userMapper.toDto(user);
        }
        return UserDto.builder().username(userDetails.getUsername()).build();
    }

    /**
     * Determines whether a user is authorized to access a resource by comparing
     * the authorities required for the resource with the authorities possessed by the user.
     *
     * @param resourceAuthorities a list of authority strings required to access the resource
     * @param userAuthorities a list of authority strings that the user possesses
     * @return true if the user has at least one authority that matches the required authorities, false otherwise
     */
    private boolean isUserAuthorized(List<String> resourceAuthorities, List<String> userAuthorities) {
        return !Collections.disjoint(resourceAuthorities, userAuthorities);
    }
}
