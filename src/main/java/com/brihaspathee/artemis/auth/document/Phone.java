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
public class Phone {

    /**
     * Represents the type of phone (e.g., mobile, home, work).
     *
     * This field must comply with the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 3 characters.
     */
    @NotNull(message = "Phone type cannot be null")
    @NotBlank(message = "Phone type cannot be blank")
    @Size(min = 3,  message = "Phone type should be at least 3 characters")
    private String phoneType;

    /**
     * Represents the phone number associated with a phone.
     *
     * This field is subject to the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 10 characters.
     */
    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10,  message = "Phone number should be at least 10 characters")
    private String phoneNumber;
}
