package net.techmentor.cases_service.cases.internal.domain.events;

import net.techmentor.cases_service.cases.internal.domain.model.Contribution.Contribution;
import net.techmentor.cases_service.cases.internal.domain.model.Contribution.ContributionId;

import java.util.UUID;

public record ContributionConfirmed(ContributionId id, UUID contributorId) implements CaseEvent {

    public static ContributionConfirmed from(Contribution contribution) {
        return new ContributionConfirmed(
                contribution.getId(),
                contribution.getContributorId()
        );
    }

}
