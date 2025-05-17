package net.techmentor.cases_service.cases.internal.domain.model.Case;

import net.techmentor.cases_service.shared.domain.model.ValueObject;

public record CaseCode(int value) implements ValueObject {
    public CaseCode {
        if (value <= 0) {
            throw new IllegalArgumentException("Code must be greater than 0");
        }
    }
}
