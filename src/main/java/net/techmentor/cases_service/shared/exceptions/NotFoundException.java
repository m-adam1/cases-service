package net.techmentor.cases_service.shared.exceptions;

/**
 * Exception for not found errors.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String description) {
        super("Not Found: " + (description != null ? description : ""));
    }
}
