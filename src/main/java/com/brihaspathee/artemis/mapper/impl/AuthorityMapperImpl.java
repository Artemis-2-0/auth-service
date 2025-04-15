package com.brihaspathee.artemis.mapper.impl;

import com.brihaspathee.artemis.auth.document.Authority;
import com.brihaspathee.artemis.dto.auth.AuthorityDto;
import com.brihaspathee.artemis.mapper.interfaces.AuthorityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:13
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class AuthorityMapperImpl implements AuthorityMapper {

    /**
     * Converts an {@link Authority} entity into an {@link AuthorityDto} object.
     *
     * @param authority the {@link Authority} entity to be converted. If null, the method returns null.
     * @return the converted {@link AuthorityDto} object or null if the input is null.
     */
    @Override
    public AuthorityDto toDto(Authority authority) {
        if(authority == null){
            return null;
        }
        AuthorityDto authorityDto = AuthorityDto.builder()
                .permission(authority.getPermission())
                .build();
        return authorityDto;
    }

    /**
     * Converts an {@link AuthorityDto} object into an {@link Authority} entity.
     *
     * @param authorityDto the {@link AuthorityDto} object to be converted into an {@link Authority} entity
     * @return the converted {@link Authority} entity or null if the input is null
     */
    @Override
    public Authority toEntity(AuthorityDto authorityDto) {
        if(authorityDto == null){
            return null;
        }
        Authority authority = Authority.builder()
                .permission(authorityDto.getPermission())
                .build();
        return authority;
    }

    /**
     * Converts a list of {@link Authority} entities into a list of {@link AuthorityDto} objects.
     *
     * @param authorities the list of {@link Authority} entities to be converted
     * @return a list of corresponding {@link AuthorityDto} objects
     */
    @Override
    public List<AuthorityDto> toDtos(List<Authority> authorities) {
        return authorities.stream().map(this::toDto).toList();
    }

    /**
     * Converts a list of {@link AuthorityDto} objects into a list of {@link Authority} entities.
     *
     * @param authorities the list of {@link AuthorityDto} objects to be converted into {@link Authority} entities
     * @return a list of {@link Authority} entities corresponding to the provided {@link AuthorityDto} objects
     */
    @Override
    public List<Authority> toEntities(List<AuthorityDto> authorities) {
        return authorities.stream().map(this::toEntity).toList();
    }
}
