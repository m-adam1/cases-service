package net.techmentor.cases_service.cases.internal.domain.model.CaseDetails;

import net.techmentor.cases_service.shared.domain.extension.ValueValidator;


public record Tag(String value) {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;

    public Tag {
        ValueValidator.assertWithinRange(getClass(), value, MIN_LENGTH, MAX_LENGTH);
    }

    public static Tag create(String value) {
        return new Tag(value);
    }
}
