package com.brihaspathee.artemis.domain.document;

import com.brihaspathee.artemis.auth.document.Authority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 05:34
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.domain.document
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@Document(collection = "resource")
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    /**
     * Represents the unique identifier for the resource.
     *
     * This field serves as the primary key for the Resource entity and is used
     * to uniquely identify each resource in the system. It is mapped to the
     * "_id" field in the underlying data store.
     */
    @Id
    @Field("_id")
    private String resourceId;

    /**
     * Represents the name of a resource in the system.
     *
     * This field must comply with the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 3 characters.
     */
    @NotNull(message = "Resource name cannot be null")
    @NotBlank(message = "Resource name cannot be blank")
    @Size(min = 3,  message = "Resource name should be at least 3 characters")
    private String resourceName;;

    /**
     * Represents the description of a resource in the system.
     *
     * This field must comply with the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 10 characters.
     */
    @NotNull(message = "Resource description cannot be null")
    @NotBlank(message = "Resource description cannot be blank")
    @Size(min = 10,  message = "Resource description should be at least 10 characters")
    private String resourceDescription;

    /**
     * Represents the type of the resource.
     *
     * This field must comply with the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 3 characters.
     */
    @NotNull(message = "Resource type cannot be null")
    @NotBlank(message = "Resource type cannot be blank")
    @Size(min = 3,  message = "Resource type should be at least 3 characters")
    private String resourceType;

    /**
     * Represents the URI of the resource.
     *
     * This field must adhere to the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 10 characters.
     */
    @NotNull(message = "Resource url cannot be null")
    @NotBlank(message = "Resource url cannot be blank")
    @Size(min = 10,  message = "Resource url should be at least 10 characters")
    private String resourceUri;

    /**
     * Represents the owner of the resource.
     *
     * This field must comply with the following validation constraints:
     * - It cannot be null.
     * - It cannot be blank.
     * - It must have a minimum length of 3 characters.
     */
    @NotNull(message = "Resource owner cannot be null")
    @NotBlank(message = "Resource owner cannot be blank")
    @Size(min = 3,  message = "Resource owner should be at least 3 characters")
    private String resourceOwner;

    /**
     * Represents the list of authorities associated with a resource.
     *
     * This field is a list of {@link Authority} objects and must adhere to the following validation constraints:
     * - The list cannot be null.
     * - The list must contain at least one authority.
     */
    @Size(min = 1,  message = "At least one authority is required")
    private List<Authority> authorities = new ArrayList<>();
}
