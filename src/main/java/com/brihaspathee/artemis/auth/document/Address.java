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
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    /**
     * Represents the type of address (e.g., home, work, billing, etc.).
     *
     * Validation constraints:
     * - Cannot be null.
     * - Cannot be blank.
     * - Must have a minimum length of 3 characters.
     */
    @NotNull(message = "Address type cannot be null")
    @NotBlank(message = "Address type cannot be blank")
    @Size(min = 3,  message = "Address type should be at least 3 characters")
    private String addressType;

    /**
     * Represents the first line of an address, typically used to specify the primary
     * location details such as street name and number.
     *
     * Validation constraints:
     * - Cannot be null.
     * - Cannot be blank.
     * - Must have a minimum length of 3 characters.
     */
    @NotNull(message = "Address Line 1 cannot be null")
    @NotBlank(message = "Address Line 1 cannot be blank")
    @Size(min = 3,  message = "Address Line 1 should be minimum 3 characters")
    private String addressLine1;

    /**
     * Represents the second line of an address, typically used to provide additional
     * information such as apartment number, suite, or other location details.
     *
     * Validation constraints:
     * - Maximum length is 100 characters.
     */
    @Size(max = 100)
    private String addressLine2;

    /**
     * Represents the city associated with an address.
     *
     * This field must adhere to the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 3 characters.
     */
    @NotNull(message = "City cannot be null")
    @NotBlank(message = "City cannot be blank")
    @Size(min = 3,  message = "City should be minimum 3 characters")
    private String city;

    /**
     * Represents the state associated with an address.
     * The state value is mandatory and must meet the following criteria:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 2 characters.
     */
    @NotBlank(message = "State cannot be blank")
    @NotNull(message = "State cannot be null")
    @Size(min = 2,  message = "State should be 2 characters")
    private String state;

    /**
     * Represents the postal Zip Code associated with an address.
     * The Zip Code must not be null or blank and must contain at least 5 characters.
     */
    @NotBlank(message = "Zip Code cannot be blank")
    @NotNull(message = "Zip Code cannot be null")
    @Size(min = 5,  message = "Zip Code should be 5 characters")
    private String zipCode;
}
