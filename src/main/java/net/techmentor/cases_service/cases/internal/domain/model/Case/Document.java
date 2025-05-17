package net.techmentor.cases_service.cases.internal.domain.model.Case;

import net.techmentor.cases_service.shared.domain.extension.ValueValidator;

import java.util.regex.Pattern;

public record Document(String value) {
    private static final Pattern URL_PATTERN = Pattern.compile(
            "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)"
    );

    public Document {
        ValueValidator.assertNotEmpty(value, getClass());
        ValueValidator.assertValidFormat(value, URL_PATTERN, getClass());
    }

    public static Document create(String value) {
        return new Document(value);
    }
}
