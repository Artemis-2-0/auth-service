package com.brihaspathee.artemis.mapper.interfaces;

import com.brihaspathee.artemis.domain.document.Resource;
import com.brihaspathee.artemis.dto.resource.ResourceDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:06
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface ResourceMapper {

    /**
     * Converts a {@link Resource} entity into a {@link ResourceDto} object.
     *
     * @param resource the {@link Resource} entity to be converted
     * @return the converted {@link ResourceDto} object
     */
    ResourceDto toDto(Resource resource);

    /**
     * Converts a {@link ResourceDto} object to a {@link Resource} entity.
     *
     * @param resourceDto the {@link ResourceDto} object to be converted into a {@link Resource} entity
     * @return the converted {@link Resource} entity
     */
    Resource toEntity(ResourceDto resourceDto);

    /**
     * Converts a list of {@link Resource} entities into a list of {@link ResourceDto} objects.
     *
     * @param resourceList the list of {@link Resource} entities to be converted
     * @return a list of corresponding {@link ResourceDto} objects
     */
    List<ResourceDto> toDtos(List<Resource> resourceList);

    /**
     * Maps a list of {@link ResourceDto} objects to a list of {@link Resource} entities.
     *
     * @param resourceDtoList the list of {@link ResourceDto} objects to be converted into {@link Resource} entities
     * @return a list of {@link Resource} entities corresponding to the provided {@link ResourceDto} objects
     */
    List<Resource> toEntities(List<ResourceDto> resourceDtoList);
}
