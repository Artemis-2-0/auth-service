package com.brihaspathee.artemis.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, April 2025
 * Time: 2:12 PM
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.exception
 * To change this template use File | Settings | File and Code Template
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message that provides information about the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }


    /**
     * Constructs a new UserNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message, which provides more information about the exception.
     * @param cause the cause of the exception, which can be used to indicate the underlying issue.
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
