package com.brihaspathee.artemis.auth.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
public class Authority {

    /**
     * Represents a permission assigned to an authority.
     *
     * This field must adhere to the following validation constraints:
     * - It cannot be blank.
     * - It cannot be null.
     * - It must have a minimum length of 3 characters.
     */
    @NotBlank(message = "Authority cannot be blank")
    @NotNull(message = "Permission cannot be null")
    @Size(min = 3,  message = "Authority should be at least 3 characters")
    private String permission;
}
