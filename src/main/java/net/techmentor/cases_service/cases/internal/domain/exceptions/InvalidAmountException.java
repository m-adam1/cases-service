package net.techmentor.cases_service.cases.internal.domain.exceptions;

import net.techmentor.cases_service.shared.exceptions.BusinessRuleException;

public class InvalidAmountException extends BusinessRuleException {

    public InvalidAmountException(int value) {
        super(value + " is not a valid amount");
    }
}
