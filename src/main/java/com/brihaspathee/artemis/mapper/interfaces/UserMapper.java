package com.brihaspathee.artemis.mapper.interfaces;

import com.brihaspathee.artemis.auth.document.User;
import com.brihaspathee.artemis.dto.auth.UserDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:51
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface UserMapper {

    /**
     * Converts a {@link User} entity into a {@link UserDto} object.
     *
     * @param user the {@link User} entity to be converted
     * @return the converted {@link UserDto} object
     */
    UserDto toDto(User user);
}
