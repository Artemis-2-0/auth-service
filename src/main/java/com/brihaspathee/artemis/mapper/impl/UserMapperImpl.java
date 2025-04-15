package com.brihaspathee.artemis.mapper.impl;

import com.brihaspathee.artemis.auth.document.Role;
import com.brihaspathee.artemis.auth.document.User;
import com.brihaspathee.artemis.dto.auth.RoleDto;
import com.brihaspathee.artemis.dto.auth.UserDto;
import com.brihaspathee.artemis.mapper.interfaces.RoleMapper;
import com.brihaspathee.artemis.mapper.interfaces.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:55
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    /**
     * The RoleMapper is a utility component for converting between {@link Role} entities and their corresponding
     * {@link RoleDto} representations, as well as handling collections of these objects. It provides methods for:
     * - Entity-to-DTO conversion
     * - DTO-to-Entity conversion
     * - Batch processing for both entity and DTO collections
     *
     * This field is a dependency used within the implementation of {@link UserMapperImpl} to facilitate role mapping.
     */
    private final RoleMapper roleMapper;

    /**
     * Converts a {@link User} entity into a {@link UserDto} object.
     *
     * @param user the {@link User} entity to be converted; if {@code null}, the method will return {@code null}
     * @return a {@link UserDto} object representing the provided {@link User} entity,
     *         or {@code null} if the input {@link User} is {@code null}
     */
    @Override
    public UserDto toDto(User user) {
        if(user == null){
            return null;
        }
        UserDto userDto = UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roleMapper.toDtos(user.getRoles()))
                .build();
        return userDto;
    }
}
