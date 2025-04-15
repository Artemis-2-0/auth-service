package com.brihaspathee.artemis.auth.document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 10:39 AM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.document
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Principal {

    /**
     * Represents the unique identifier for a user in the system.
     *
     * This field serves as the primary key for the {@code User} entity.
     * It is mapped to the underlying database field named "_id".
     */
    @Id
    @Field("_id")
    private String userId;

    /**
     * Represents the username of the user.
     *
     * The username is a unique identifier for the user in the system
     * and is typically used for authentication and identification purposes.
     */
    private String username;

    /**
     * Represents the password of the user.
     *
     * This field is used to store the user's password in a secure manner.
     * It is typically encrypted or hashed before storage to ensure confidentiality
     * and to prevent unauthorized access.
     */
    private String password;

    /**
     * Indicates whether the user's account is not expired.
     *
     * If set to true, the account is considered active and accessible.
     * If set to false, the account is considered expired and may be subject to restrictions.
     */
    private boolean accountNotExpired = true;

    /**
     * Indicates whether the user's account is not locked.
     *
     * This flag determines if the user is allowed to access the system.
     * A value of {@code true} means the account is not locked and can be used,
     * while {@code false} indicates that the account is locked and the user
     * cannot log in until the account is unlocked.
     */
    private boolean accountNotLocked = true;

    /**
     * Indicates whether the user's credentials are not expired.
     *
     * This field is used to determine if the credentials associated with the user
     * (e.g., password or other authentication details) are still valid. A value of
     * {@code true} implies that the credentials have not expired, while a value of
     * {@code false} indicates that they are expired, and, as a result, the user
     * may not be able to authenticate successfully.
     */
    private boolean credentialsNotExpired = true;

    /**
     * Indicates whether the user account is enabled.
     *
     * If the value is {@code true}, the account is active and usable.
     * If the value is {@code false}, the account is disabled and cannot be used
     * for authentication or authorization purposes.
     */
    private boolean enabled = true;

    /**
     * Represents the profile information associated with a user.
     *
     * This field is mandatory and cannot be null. It acts as a link between
     * the user and the relevant personal or professional information stored
     * within the {@link Profile} entity.
     */
    @DBRef
    private String profile;

    /**
     * Represents the collection of roles assigned to the user.
     *
     * This field enables the authorization mechanisms by defining
     * a list of {@link Role} objects associated with the user.
     *
     * Validation constraints:
     * - The list cannot be null.
     * - The list must contain at least one role.
     */
    @Size(min = 1, message = "At least one role is required")
    private List<Role> roles;

    /**
     * Retrieves the name of the user.
     *
     * @return the username of the user
     */
    @Override
    public String getName() {
        return this.username;
    }

    /**
     * Determines if this principal implies the specified subject.
     *
     * @param subject the subject to be checked, typically representing
     *                a user or system entity whose access is being evaluated
     * @return true if this principal implies the specified subject, false otherwise
     */
    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }

    /**
     * Returns the collection of authorities granted to the user.
     * This includes all permissions associated with the roles assigned to the user,
     * after mapping each role's authorities to a specific granted authority representation.
     *
     * @return a collection of {@link GrantedAuthority} objects representing the authorities granted to the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getAuthorities)
                .flatMap(List::stream)
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves the password of the user.
     *
     * @return the password of the user as a String
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return the username of the user as a String
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Determines if the user's account is not expired.
     *
     * @return true if the account is not expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNotExpired;
    }

    /**
     * Determines if the user's account is not locked.
     *
     * @return true if the account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNotLocked;
    }

    /**
     * Determines if the user's credentials are not expired.
     *
     * @return true if the credentials are not expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNotExpired;
    }

    /**
     * Determines if the user account is enabled.
     *
     * @return true if the account is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
