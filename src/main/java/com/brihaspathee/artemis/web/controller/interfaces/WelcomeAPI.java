package com.brihaspathee.artemis.web.controller.interfaces;

import com.brihaspathee.artemis.dto.WelcomeDto;
import com.brihaspathee.artemis.web.response.ArtemisAPIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 08, April 2025
 * Time: 10:51
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.web.controller.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/artemis/auth/public")
@Validated
public interface WelcomeAPI {

    /**
     * Endpoint to return a welcome message from the Artemis API.
     *
     * @return ResponseEntity containing an ArtemisAPIResponse with a String message
     *         that holds the welcome message.
     */
    @GetMapping("/welcome")
    ResponseEntity<ArtemisAPIResponse<WelcomeDto>> welcome();

}
