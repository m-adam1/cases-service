package net.techmentor.cases_service.cases.internal.domain.events;

import net.techmentor.cases_service.shared.domain.model.DomainEvent;

public sealed interface CaseEvent extends DomainEvent
        permits CaseClosed, CaseDeleted, CaseOpened, CaseUpdated, ContributionConfirmed, ContributionMade, ContributionPaid, ContributionReminded {
}
