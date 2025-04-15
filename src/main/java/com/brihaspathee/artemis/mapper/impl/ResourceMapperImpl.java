package com.brihaspathee.artemis.mapper.impl;

import com.brihaspathee.artemis.auth.document.Authority;
import com.brihaspathee.artemis.domain.document.Resource;
import com.brihaspathee.artemis.dto.auth.AuthorityDto;
import com.brihaspathee.artemis.dto.resource.ResourceDto;
import com.brihaspathee.artemis.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.artemis.mapper.interfaces.ResourceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:08
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceMapperImpl implements ResourceMapper {

    /**
     * Maps between {@link Authority} and {@link AuthorityDto} objects, as well as their respective collections.
     * This field is used to delegate authority-related conversion logic within the {@link ResourceMapperImpl}.
     */
    private final AuthorityMapper authorityMapper;

    /**
     * Converts a {@link Resource} entity into a {@link ResourceDto} object.
     *
     * @param resource the {@link Resource} entity to be converted. If null, the method returns null.
     * @return the converted {@link ResourceDto} object or null if the input is null.
     */
    @Override
    public ResourceDto toDto(Resource resource) {
        if(resource == null){
            return null;
        }
        ResourceDto resourceDto = ResourceDto.builder()
                .resourceId(resource.getResourceId())
                .resourceName(resource.getResourceName())
                .resourceDescription(resource.getResourceDescription())
                .resourceUri(resource.getResourceUri())
                .resourceOwner(resource.getResourceOwner())
                .authorities(authorityMapper.toDtos(resource.getAuthorities()))
                .build();
        return resourceDto;
    }

    /**
     * Converts a {@link ResourceDto} object into a {@link Resource} entity.
     *
     * @param resourceDto the {@link ResourceDto} object to be converted to a {@link Resource} entity.
     *                    If the input is null, the method will return null.
     * @return a {@link Resource} entity constructed from the given {@link ResourceDto}, or null if the input is null.
     */
    @Override
    public Resource toEntity(ResourceDto resourceDto) {
        if(resourceDto == null){
            return null;
        }
        Resource resource = Resource.builder()
                .resourceId(resourceDto.getResourceId())
                .resourceName(resourceDto.getResourceName())
                .resourceDescription(resourceDto.getResourceDescription())
                .resourceUri(resourceDto.getResourceUri())
                .resourceOwner(resourceDto.getResourceOwner())
                .authorities(authorityMapper.toEntities(resourceDto.getAuthorities()))
                .build();
        return resource;
    }

    /**
     * Converts a list of {@link Resource} entities into a list of {@link ResourceDto} objects.
     *
     * @param resourceList the list of {@link Resource} entities to be converted
     * @return a list of corresponding {@link ResourceDto} objects
     */
    @Override
    public List<ResourceDto> toDtos(List<Resource> resourceList) {
        return resourceList.stream().map(this::toDto).toList();
    }

    /**
     * Converts a list of {@link ResourceDto} objects into a list of {@link Resource} entities.
     *
     * @param resourceDtoList the list of {@link ResourceDto} objects to be converted
     * @return a list of {@link Resource} entities corresponding to the provided {@link ResourceDto} objects
     */
    @Override
    public List<Resource> toEntities(List<ResourceDto> resourceDtoList) {
        return resourceDtoList.stream().map(this::toEntity).toList();
    }
}
