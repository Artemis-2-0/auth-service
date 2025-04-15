package com.brihaspathee.artemis.domain.repository;

import com.brihaspathee.artemis.domain.document.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 14:55
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {

    /**
     * Finds a resource based on its unique resource URI.
     *
     * @param resourceURI the URI of the resource to be retrieved; must not be null or blank
     * @return an {@code Optional} containing the {@code Resource} if found, or an empty {@code Optional} if not found
     */
    Optional<Resource> findByResourceUri(String resourceURI);
}
