package com.brihaspathee.artemis.auth.repository;

import com.brihaspathee.artemis.auth.document.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 2:04 PM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
}
