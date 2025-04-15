package com.brihaspathee.artemis.mapper.interfaces;

import com.brihaspathee.artemis.auth.document.Role;
import com.brihaspathee.artemis.dto.auth.RoleDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:50
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface RoleMapper {

    /**
     * Converts a {@link Role} entity into a {@link RoleDto} object.
     *
     * @param role the {@link Role} entity to be converted
     * @return the converted {@link RoleDto} object
     */
    RoleDto toDto(Role role);

    /**
     * Converts a {@link RoleDto} object to a {@link Role} entity.
     *
     * @param roleDto the {@link RoleDto} object to be converted into a {@link Role} entity
     * @return the converted {@link Role} entity
     */
    Role toEntity(RoleDto roleDto);

    /**
     * Converts a list of {@link Role} entities into a list of {@link RoleDto} objects.
     *
     * @param roles the list of {@link Role} entities to be converted
     * @return a list of {@link RoleDto} objects corresponding to the provided {@link Role} entities
     */
    List<RoleDto> toDtos(List<Role> roles);

    /**
     * Converts a list of {@link RoleDto} objects into a list of {@link Role} entities.
     *
     * @param roles the list of {@link RoleDto} objects to be converted
     * @return a list of {@link Role} entities corresponding to the provided {@link RoleDto} objects
     */
    List<Role> toEntities(List<RoleDto> roles);
}
