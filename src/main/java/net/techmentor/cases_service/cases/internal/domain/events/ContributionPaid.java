package net.techmentor.cases_service.cases.internal.domain.events;

import net.techmentor.cases_service.cases.internal.domain.model.Contribution.Contribution;
import net.techmentor.cases_service.cases.internal.domain.model.Contribution.ContributionId;

import java.util.UUID;

public record ContributionPaid(ContributionId id, UUID contributorId) implements CaseEvent {

    public static ContributionPaid from(Contribution contribution) {
        return new ContributionPaid(
                contribution.getId(),
                contribution.getContributorId()
        );
    }

}
