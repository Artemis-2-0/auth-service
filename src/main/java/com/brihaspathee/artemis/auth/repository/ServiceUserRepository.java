package com.brihaspathee.artemis.auth.repository;

import com.brihaspathee.artemis.auth.document.ServiceUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, April 2025
 * Time: 13:24
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface ServiceUserRepository extends MongoRepository<ServiceUser, String> {

    /**
     * Finds a ServiceUser entity based on the provided service name.
     *
     * @param serviceName the name of the service associated with the user to be retrieved
     * @return an Optional containing the ServiceUser if found, or an empty Optional if no such user exists
     */
    Optional<ServiceUser> findServiceUserByServiceName(String serviceName);
}
