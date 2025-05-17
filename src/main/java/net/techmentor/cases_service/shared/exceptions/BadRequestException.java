package net.techmentor.cases_service.shared.exceptions;

/**
 * Exception for bad request errors.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String description) {
        super("Bad Request: " + (description != null ? description : ""));
    }
}
