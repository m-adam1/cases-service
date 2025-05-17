package net.techmentor.cases_service.cases.internal.domain.events;

import net.techmentor.cases_service.cases.internal.domain.model.Contribution.Contribution;
import net.techmentor.cases_service.cases.internal.domain.model.Contribution.ContributionId;

import java.util.UUID;

public record ContributionReminded(ContributionId id, UUID contributorId) implements CaseEvent {

    public static ContributionReminded from(Contribution contribution) {
        return new ContributionReminded(
                contribution.getId(),
                contribution.getContributorId()
        );
    }

}
