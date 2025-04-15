package com.brihaspathee.artemis.auth;

import com.brihaspathee.artemis.web.exception.APIException;
import com.brihaspathee.artemis.web.response.ArtemisAPIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 5:25 PM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.auth
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Configuration
public class ArtemisAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * ObjectMapper instance used for JSON parsing and serialization.
     * This instance is globally available and statically initialized to handle
     * JSON data formatting and conversion in the application.
     * Provides methods for reading from and writing to JSON in a convenient manner.
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Handles authentication failures by generating an appropriate response
     * based on the type of {@link AuthenticationException} encountered.
     * Logs the request URL and provides detailed error responses for different
     * exception scenarios such as invalid credentials or insufficient authentication.
     *
     * @param request the HTTP request made by the client
     * @param response the HTTP response object to which the error details are written
     * @param authException the exception that caused the authentication failure
     * @throws IOException if an input or output error occurs while handling the response
     * @throws ServletException if the implementation detects an application error
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        log.info("Request URL:{}", request.getRequestURL());
        if(authException instanceof BadCredentialsException){
            generateResponse(response, "1000001", "Invalid Credentials Provided", HttpStatus.UNAUTHORIZED, HttpServletResponse.SC_UNAUTHORIZED,
                    "The username or password provided is incorrect",
                    "The username or password provided is incorrect",
                    "The username or password provided is incorrect");
        }else if (authException instanceof InsufficientAuthenticationException){
            generateResponse(response, "1000002", "Invalid JWT provided", HttpStatus.UNAUTHORIZED, HttpServletResponse.SC_UNAUTHORIZED,
                    "The JWT provided in the request header is not valid",
                    "Invalid JWT provided",
                    "Invalid JWT provided");
        }else {
            log.error("Authentication Exception occurred", authException);
            generateResponse(response, "1000003", "Authentication Exception occurred", HttpStatus.UNAUTHORIZED, HttpServletResponse.SC_UNAUTHORIZED,
                    "Authentication Exception occurred",
                    "Authentication Exception occurred",
                    "Authentication Exception occurred");
        }

    }

    /**
     * Generates a structured JSON response for error handling and writes it to the
     * provided HttpServletResponse. The response includes details such as error codes,
     * messages, developer information, and HTTP status to be sent to the client.
     *
     * @param response          the HttpServletResponse object to write the JSON response to
     * @param errorCode         a string representing the specific error code
     * @param errorMessage      a detailed error message describing the issue
     * @param httpStatus        the HTTP status to be set in the response
     * @param statusCode        an integer representing the specific status code
     * @param developerMessage  a message intended for developers to provide additional context
     * @param responseMessage   a general message to summarize the response
     * @param responseReason    a description of the reason for the error or response
     * @throws IOException      if an input or output exception occurs while writing the response
     */
    private static void generateResponse(HttpServletResponse response,
                                         String errorCode,
                                         String errorMessage,
                                         HttpStatus httpStatus,
                                         int statusCode,
                                         String developerMessage,
                                         String responseMessage,
                                         String responseReason) throws IOException {
        APIException apiException = APIException.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
        ArtemisAPIResponse<APIException> apiResponse = ArtemisAPIResponse.<APIException>builder()
                .statusCode(statusCode)
                .status(httpStatus)
                .response(apiException)
                .developerMessage(developerMessage)
                .message(responseMessage)
                .reason(responseReason)
                .build();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));

    }
}
