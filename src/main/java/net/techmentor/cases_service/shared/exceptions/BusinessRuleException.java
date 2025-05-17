package net.techmentor.cases_service.shared.exceptions;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String description) {
        super(description);
    }
}
