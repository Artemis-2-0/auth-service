package com.brihaspathee.artemis.auth.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 10:52 AM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.document
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    /**
     * Represents the type of email (e.g., personal, work, official).
     *
     * This field is subject to the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 3 characters.
     */
    @NotNull(message = "Email type cannot be null")
    @NotBlank(message = "Email type cannot be blank")
    @Size(min = 3,  message = "Email type should be at least 3 characters")
    private String emailType;

    /**
     * Represents the email address associated with an entity.
     *
     * Validation constraints:
     * - Must be a valid email address as per the format defined in RFC 5322.
     * - Cannot be null.
     * - Cannot be blank.
     */
    @jakarta.validation.constraints.Email(message = "Email address is invalid")
    @NotNull(message = "Email address cannot be null")
    @NotBlank(message = "Email address cannot be blank")
    private String emailAddress;
}
