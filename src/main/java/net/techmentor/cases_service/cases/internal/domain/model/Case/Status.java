package net.techmentor.cases_service.cases.internal.domain.model.Case;

public enum Status {
    DRAFT,
    OPENED,
    CLOSED;

    public static Status fromString(String status) {
        return valueOf(status.toUpperCase());
    }
}
