package com.brihaspathee.artemis.mapper.interfaces;

import com.brihaspathee.artemis.auth.document.Authority;
import com.brihaspathee.artemis.dto.auth.AuthorityDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:11
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AuthorityMapper {

    /**
     * Converts an {@link Authority} entity into an {@link AuthorityDto} object.
     *
     * @param authority the {@link Authority} entity to be converted
     * @return the converted {@link AuthorityDto} object
     */
    AuthorityDto toDto(Authority authority);

    /**
     * Converts an {@link AuthorityDto} object to an {@link Authority} entity.
     *
     * @param authorityDto the {@link AuthorityDto} object to be converted into an {@link Authority} entity
     * @return the converted {@link Authority} entity
     */
    Authority toEntity(AuthorityDto authorityDto);

    /**
     * Converts a list of {@link Authority} entities into a list of {@link AuthorityDto} objects.
     *
     * @param authorities the list of {@link Authority} entities to be converted
     * @return a list of corresponding {@link AuthorityDto} objects
     */
    List<AuthorityDto> toDtos(List<Authority> authorities);

    /**
     * Converts a list of {@link AuthorityDto} objects to a list of {@link Authority} entities.
     *
     * @param authorities the list of {@link AuthorityDto} objects to be converted into {@link Authority} entities
     * @return a list of {@link Authority} entities corresponding to the provided {@link AuthorityDto} objects
     */
    List<Authority> toEntities(List<AuthorityDto> authorities);
}
