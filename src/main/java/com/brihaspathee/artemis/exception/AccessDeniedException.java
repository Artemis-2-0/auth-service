package com.brihaspathee.artemis.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, April 2025
 * Time: 15:48
 * Project: artemis
 * Package Name: com.brihaspathee.artemis.exception
 * To change this template use File | Settings | File and Code Template
 */
public class AccessDeniedException extends RuntimeException {

    /**
     * Constructs a new AccessDeniedException with the specified detail message.
     *
     * @param message the detail message that provides information about the exception.
     */
    public AccessDeniedException(String message) {
        super(message);
    }

    /**
     * Constructs a new AccessDeniedException with the specified detail message and cause.
     *
     * @param message the detail message that provides information about the exception.
     * @param cause the cause of the exception, which can be used to indicate the underlying issue.
     */
    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
