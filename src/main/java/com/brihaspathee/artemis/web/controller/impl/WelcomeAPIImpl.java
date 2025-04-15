package com.brihaspathee.artemis.web.controller.impl;

import com.brihaspathee.artemis.dto.WelcomeDto;
import com.brihaspathee.artemis.web.controller.interfaces.WelcomeAPI;
import com.brihaspathee.artemis.web.response.ArtemisAPIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 08, April 2025
 * Time: 10:52
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.web.controller.impl
 * To change this template use File | Settings | File and Code Template
 */
@RestController
public class WelcomeAPIImpl implements WelcomeAPI {

    /**
     * Returns a welcome message encapsulated within an ArtemisAPIResponse.
     *
     * @return ResponseEntity containing the ArtemisAPIResponse with the welcome message
     *         as a WelcomeDto object.
     */
    @Override
    public ResponseEntity<ArtemisAPIResponse<WelcomeDto>> welcome() {
        WelcomeDto welcomeDto = new WelcomeDto("Welcome to Artemis Authentication Service!");
        ArtemisAPIResponse<WelcomeDto> apiResponse = ArtemisAPIResponse.<WelcomeDto>builder()
                .response(welcomeDto)
                .developerMessage("Successfully fetched the welcome message")
                .statusCode(200)
                .message("Success")
                .reason("Success")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
