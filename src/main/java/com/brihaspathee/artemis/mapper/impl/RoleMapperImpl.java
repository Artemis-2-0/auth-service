package com.brihaspathee.artemis.mapper.impl;

import com.brihaspathee.artemis.auth.document.Authority;
import com.brihaspathee.artemis.auth.document.Role;
import com.brihaspathee.artemis.dto.auth.AuthorityDto;
import com.brihaspathee.artemis.dto.auth.RoleDto;
import com.brihaspathee.artemis.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.artemis.mapper.interfaces.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:51
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RoleMapperImpl implements RoleMapper {

    /**
     * The AuthorityMapper is a component used for mapping between {@link Authority} and {@link AuthorityDto} objects,
     * as well as for their respective collections. It serves as a utility to handle the conversion logic for authority-related
     * data within the application.
     */
    private final AuthorityMapper authorityMapper;

    /**
     * Converts a {@link Role} entity into a {@link RoleDto} object.
     *
     * @param role the {@link Role} entity to be converted. If null, the method returns null.
     * @return the converted {@link RoleDto} object or null if the input is null.
     */
    @Override
    public RoleDto toDto(Role role) {
        if(role == null){
            return null;
        }
        RoleDto roleDto = RoleDto.builder()
                .roleName(role.getRoleName())
                .authorities(authorityMapper.toDtos(role.getAuthorities()))
                .build();
        return null;
    }

    /**
     * Converts a {@link RoleDto} object into a {@link Role} entity.
     *
     * @param roleDto the {@link RoleDto} object to be converted to a {@link Role} entity.
     *                If the input is null, the method will return null.
     * @return a {@link Role} entity constructed from the given {@link RoleDto},
     *         or null if the input is null.
     */
    @Override
    public Role toEntity(RoleDto roleDto) {
        if(roleDto == null){
            return null;
        }
        Role role = Role.builder()
                .roleName(roleDto.getRoleName())
                .authorities(authorityMapper.toEntities(roleDto.getAuthorities()))
                .build();
        return role;
    }

    /**
     * Converts a list of {@link Role} entities into a list of {@link RoleDto} objects.
     *
     * @param roles the list of {@link Role} entities to be converted
     * @return a list of corresponding {@link RoleDto} objects
     */
    @Override
    public List<RoleDto> toDtos(List<Role> roles) {
        return roles.stream().map(this::toDto).toList();
    }

    /**
     * Converts a list of {@link RoleDto} objects into a list of {@link Role} entities.
     *
     * @param roles the list of {@link RoleDto} objects to be converted
     * @return a list of {@link Role} entities corresponding to the provided {@link RoleDto} objects
     */
    @Override
    public List<Role> toEntities(List<RoleDto> roles) {
        return roles.stream().map(this::toEntity).toList();
    }
}
