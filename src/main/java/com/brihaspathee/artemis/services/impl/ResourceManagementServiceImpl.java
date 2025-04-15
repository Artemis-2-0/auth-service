package com.brihaspathee.artemis.services.impl;

import com.brihaspathee.artemis.domain.document.Resource;
import com.brihaspathee.artemis.domain.repository.ResourceRepository;
import com.brihaspathee.artemis.dto.resource.ResourceDto;
import com.brihaspathee.artemis.mapper.interfaces.ResourceMapper;
import com.brihaspathee.artemis.services.interfaces.ResourceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 14:56
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.services.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceManagementServiceImpl implements ResourceManagementService {

    /**
     * Repository interface to perform CRUD operations and queries on the Resource entities stored in the database.
     * Acts as a data access layer for manipulating resource data.
     */
    private final ResourceRepository resourceRepository;

    /**
     * The {@code ResourceMapper} is responsible for mapping between {@link Resource} entities and their corresponding
     * {@link ResourceDto} objects. It facilitates bidirectional conversion, enabling seamless transformation of
     * domain-level entities to data transfer objects and vice versa.
     */
    private final ResourceMapper resourceMapper;

    /**
     * Retrieves the details of a resource based on the provided URI.
     *
     * @param resourceURI the unique URI of the resource to retrieve; must not be null or blank
     * @return a {@code ResourceDto} object containing the details of the resource, or null if the resource is not found
     */
    @Override
    public ResourceDto getResourceDetails(String resourceURI) {
        Optional<Resource> optionalResource =  resourceRepository.findByResourceUri(resourceURI);
        if(optionalResource.isPresent()){
            Resource resource = optionalResource.get();
            return resourceMapper.toDto(resource);
        }else {
            return null;
        }
    }
}
