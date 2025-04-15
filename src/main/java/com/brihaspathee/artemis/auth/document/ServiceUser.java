package com.brihaspathee.artemis.auth.document;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, April 2025
 * Time: 12:54
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.document
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@Document(collection = "service-users")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUser implements UserDetails, Principal {

    /**
     * Represents the unique identifier for a service user in the system.
     *
     * This field is annotated with:
     * - {@code @Id}: Designates this field as the primary key for persistence.
     * - {@code @Field("_id")}: Maps the field to the "_id" field in the underlying data store.
     *
     * The {@code serviceUserId} serves as a key component for identifying and managing service user entities
     * within the application.
     */
    @Id
    @Field("_id")
    private String serviceUserId;

    /**
     * Represents the name of the service in the system.
     *
     * This field is used to identify the specific service
     * associated with the user. It corresponds to the username
     * in the context of user authentication and authorization.
     */
    private String serviceName;

    /**
     * Represents the password associated with a service user.
     *
     * This field is used to authenticate the service user and should be
     * stored and transmitted securely to prevent unauthorized access.
     */
    private String servicePassword;

    /**
     * Represents the owner of the service.
     *
     * This field contains the name or identifier of the individual or entity
     * that owns or is associated with the service user.
     */
    private String serviceOwner;

    /**
     * Represents the email address of the owner of the service user.
     *
     * This field is used to store the contact email of the person or entity
     * responsible for the service user. It can be utilized for communication
     * or identification purposes related to the service.
     */
    private String serviceOwnerEmail;

    /**
     * Indicates whether the user's account is not expired.
     *
     * This variable is used to determine the validity of the account
     * within the system. If set to true, the account is considered to be
     * non-expired and active. If set to false, the account is treated as expired
     * and access is restricted accordingly.
     */
    private boolean accountNotExpired = true;

    /**
     * Indicates whether the service user's account is not locked.
     *
     * If the value is true, the account is considered to be active and not
     * restricted by lockout policies. If false, the account is locked
     * and the user may not authenticate or access the system based on the
     * current lock conditions.
     */
    private boolean accountNotLocked = true;

    /**
     * Indicates whether the user's credentials are non-expired.
     *
     * This variable is set to true by default, implying that the credentials
     * are valid and have not expired. It can be used as part of the validation
     * process to ensure the user's credentials are still valid for authentication.
     */
    private boolean credentialsNotExpired = true;

    /**
     * Represents the status of whether the service user is enabled or disabled.
     *
     * This value determines if the user is active and allowed to interact
     * with the system. A `true` value indicates the user is enabled, while
     * a `false` value indicates the user is disabled.
     */
    private boolean enabled = true;

    /**
     * Represents the list of authorities assigned to the service user.
     *
     * Constraints:
     * - Must contain at least one authority.
     *
     * Each authority defines specific permissions granted to the service user.
     * This field is essential for defining and enforcing access control rules
     * in the system.
     */
    @Size(min = 1, message = "At least one authority is required")
    private List<Authority> authorities;

    /**
     * Retrieves the name of the service user.
     *
     * @return the service name associated with the user.
     */
    @Override
    public String getName() {
        return this.serviceName;
    }

    /**
     * Determines whether the given subject is implied by this principal.
     *
     * @param subject the {@link Subject} to be checked against this principal
     * @return true if the principal implies the provided subject, otherwise false
     */
    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }

    /**
     * Retrieves the collection of granted authorities for the current service user.
     * Each authority is mapped to a {@link SimpleGrantedAuthority} instance based on the permissions
     * associated with the authorities assigned to the user.
     *
     * @return a collection of {@link GrantedAuthority} objects representing the permissions
     *         of the service user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves the password associated with the service user.
     *
     * @return the service user's password.
     */
    @Override
    public String getPassword() {
        return this.servicePassword;
    }

    /**
     * Retrieves the username associated with the service user.
     *
     * @return the service user's username, which corresponds to the serviceName.
     */
    @Override
    public String getUsername() {
        return this.serviceName;
    }

    /**
     * Determines whether the user's account is non-expired.
     *
     * @return true if the account is non-expired, otherwise false.
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNotExpired;
    }

    /**
     * Determines whether the user's account is non-locked.
     *
     * @return true if the account is not locked, otherwise false.
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNotLocked;
    }

    /**
     * Determines whether the user's credentials are non-expired.
     *
     * @return true if the credentials are non-expired, otherwise false.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNotExpired;
    }

    /**
     * Determines whether the user is enabled or not.
     *
     * @return true if the user is enabled, otherwise false.
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
