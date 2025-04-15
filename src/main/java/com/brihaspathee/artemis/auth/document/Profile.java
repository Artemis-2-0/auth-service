package com.brihaspathee.artemis.auth.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

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
@Document(collection = "profile")
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    /**
     * Represents the unique identifier for a profile.
     *
     * This variable is annotated as the primary key for the profile entity
     * and is mapped to the "_id" field in the database.
     */
    @Id
    @Field("_id")
    private String profileId;

    /**
     * Represents the first name of a profile.
     *
     * This field must adhere to the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 3 characters.
     */
    @NotBlank(message = "First name cannot be blank")
    @NotNull(message = "First name cannot be null")
    @Size(min = 3, message = "First name has to be minimum of 3 characters")
    private String firstName;

    /**
     * Represents the middle name of an individual in a profile.
     *
     * This field is optional and can be used when an individual has a middle name
     * that needs to be stored or accessed within the profile context.
     */
    private String middleName;

    /**
     * Represents the last name of an individual in a profile.
     *
     * Validation constraints:
     * - This field cannot be null.
     * - This field cannot be blank.
     * - This field must have a minimum length of 3 characters.
     */
    @NotBlank(message = "Last name cannot be blank")
    @NotNull(message = "Last name cannot be null")
    @Size(min = 3, message = "Last name has to be minimum of 3 characters")
    private String lastName;

    /**
     * Represents the gender of an individual in a profile.
     *
     * Validation constraints:
     * - This field cannot be null.
     * - This field cannot be blank.
     * - This field must have a minimum length of 2 characters.
     */
    @NotNull(message = "Gender cannot be null")
    @NotBlank(message = "Gender cannot be blank")
    @Size(min = 2, message = "Gender has to be minimum of 3 characters")
    private String gender;

    /**
     * Represents the date of birth associated with a profile.
     *
     * Validation constraints:
     * - This field cannot be null.
     *
     * This field is used to denote the individual's date of birth and plays a role
     * in various profile-related functionalities such as age calculations and eligibility checks.
     */
    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

    /**
     * Represents a list of addresses associated with a profile.
     *
     * Validation constraints:
     * - The list must contain at least one address.
     * - Each address in the list must adhere to the constraints defined in the {@link Address} class.
     */
    @Size(min = 1, message = "At least one address is required")
    private List<Address> addresses;

    /**
     * Represents a list of phone numbers associated with a profile.
     *
     * Each phone number in the list adheres to the constraints defined in the {@link Phone} class.
     * This field allows storing multiple phone numbers for a profile, such as mobile, home, or work phones.
     */
    private List<Phone> phones;

    /**
     * Represents a list of email addresses associated with a profile.
     *
     * Validation constraints:
     * - The list must contain at least one email.
     * - Each email in the list must adhere to the constraints defined in the {@link Email} class.
     */
    @Size(min = 1, message = "At least one email is required")
    private List<Email> emails;
}
