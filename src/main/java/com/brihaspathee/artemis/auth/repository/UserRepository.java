package com.brihaspathee.artemis.auth.repository;

import com.brihaspathee.artemis.auth.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 2:03 PM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Retrieves a User from the database based on the provided username.
     *
     * @param username the unique username of the user to retrieve
     * @return an Optional containing the User if found, or an empty Optional if no user exists with the given username
     */
    Optional<User> findByUsername(String username);
}
