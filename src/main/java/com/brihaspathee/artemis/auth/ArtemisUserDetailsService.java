package com.brihaspathee.artemis.auth;

import com.brihaspathee.artemis.auth.document.ServiceUser;
import com.brihaspathee.artemis.auth.document.User;
import com.brihaspathee.artemis.auth.repository.ServiceUserRepository;
import com.brihaspathee.artemis.auth.repository.UserRepository;
import com.brihaspathee.artemis.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 2:10 PM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArtemisUserDetailsService {

    /**
     * Repository used for accessing user data in the database.
     * Provides methods to query user information based on specific criteria,
     * such as username, by utilizing the MongoDB repository.
     */
    private final UserRepository userRepository;

    /**
     * Repository used for accessing service user data in the database.
     * Provides methods to query service user information by specific criteria,
     * such as retrieving a service user based on the service name,
     * by utilizing the MongoDB repository.
     */
    private final ServiceUserRepository serviceUserRepository;


    /**
     * Loads user details based on the given username and account type from the database.
     * Depending on the account type provided, it retrieves the user details from either
     * the user repository or the service user repository.
     *
     * @param username the username of the user to load details for
     * @param accountType the type of account, which determines the repository to fetch user details from
     * @return the user details of the requested user as an instance of UserDetails
     * @throws UsernameNotFoundException if the user is not found in the database
     */
    public UserDetails loadUserByUsernameAndAccountType(String username, String accountType) throws UsernameNotFoundException {
        log.info("Loading user details for username: {} and accountType: {}, from MongoDB", username, accountType);
        if(accountType.equals("USER-ACCOUNT")){
            User user = userRepository.findByUsername(username).orElseThrow(() -> {
                log.warn("User not found in database: {}", username);
                return new UserNotFoundException("User not found in database: " + username);
            });
            log.info("User details loaded successfully for username: {}", username);
            log.info("Authorities of logged in user:{}", user.getAuthorities());
            return user;
        }else{
            ServiceUser serviceUser = serviceUserRepository.findServiceUserByServiceName(username).orElseThrow(() -> {
                log.warn("User not found in database: {}", username);
                return new UserNotFoundException("User not found in database: " + username);
            });
            log.info("User details loaded successfully for username: {}", username);
            log.info("Authorities of logged in user:{}", serviceUser.getAuthorities());
            return serviceUser;
        }


    }
}
