package net.techmentor.cases_service.shared.exceptions;

public class UnAuthorized extends RuntimeException {
    public UnAuthorized(String description) {
        super("Unauthorized: " + (description != null ? description : ""));
    }

    public UnAuthorized() {
        super("Unauthorized");
    }

}
