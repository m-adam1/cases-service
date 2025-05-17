package net.techmentor.cases_service.cases.internal.domain.model.Contribution;

import net.techmentor.cases_service.shared.domain.model.ValueObject;

import java.util.UUID;

public record ContributionId(UUID value) implements ValueObject {

    public static ContributionId generate() {
        return new ContributionId(UUID.randomUUID());
    }

}
