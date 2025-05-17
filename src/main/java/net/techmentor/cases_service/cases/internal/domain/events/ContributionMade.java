package net.techmentor.cases_service.cases.internal.domain.events;

import net.techmentor.cases_service.cases.internal.domain.model.Case.CaseCode;
import net.techmentor.cases_service.cases.internal.domain.model.Contribution.Contribution;
import net.techmentor.cases_service.cases.internal.domain.model.Contribution.ContributionId;
import net.techmentor.cases_service.cases.internal.domain.model.Contribution.MoneyValue;

import java.util.UUID;

public record ContributionMade(ContributionId id, UUID contributorId, CaseCode caseCode,
                               MoneyValue moneyValue) implements CaseEvent {

    public static ContributionMade from(Contribution contribution) {
        return new ContributionMade(
                contribution.getId(),
                contribution.getContributorId(),
                contribution.getCaseId(),
                contribution.getMoneyValue()
        );
    }
}
