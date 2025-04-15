package com.brihaspathee.artemis.services.interfaces;

import com.brihaspathee.artemis.dto.resource.ResourceDto;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 14:51
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.services.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface ResourceManagementService {


    /**
     * Retrieves the details of a resource based on the provided resource URI.
     *
     * @param resourceURI the unique URI of the resource to retrieve
     * @return a ResourceDto object containing the details of the resource
     */
    ResourceDto getResourceDetails(String resourceURI);
}
