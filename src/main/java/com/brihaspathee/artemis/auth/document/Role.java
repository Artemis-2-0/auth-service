package com.brihaspathee.artemis.auth.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 10:51 AM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.document
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    /**
     * Represents the name of a role in the system.
     *
     * This field must comply with the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 3 characters.
     */
    @NotBlank(message = "Role name cannot be blank")
    @NotNull(message = "Role name cannot be null")
    @Size(min = 3,  message = "Role name should be at least 3 characters")
    private String roleName;

    /**
     * Represents the authorities associated with a role.
     *
     * This field is a list of {@link Authority} objects which must meet the following validation constraints:
     * - The list cannot be null.
     * - The list must contain at least one authority.
     */
    @NotNull(message = "Authorities cannot be null")
    @Size(min = 1,  message = "At least one authority is required")
    private List<Authority> authorities;
}
