package net.techmentor.cases_service.cases.internal.infrastructure.repositories.mappers;

import net.techmentor.cases_service.cases.internal.domain.events.*;
import net.techmentor.cases_service.cases.shared.dtos.*;

public class CaseEventsMapper {

    public static CaseEventDto map(CaseEvent event) {
        if (event instanceof CaseOpened e) {
            return new CaseOpenedDTO(
                    e.caseCode().value(),
                    e.title(),
                    e.description()
            );
        }

        if (event instanceof CaseClosed e) {
            return new CaseClosedDTO(
                    e.caseCode().value(),
                    e.title(),
                    e.goal(),
                    e.totalValue()
            );
        }

        if (event instanceof ContributionConfirmed e) {
            return new ContributionConfirmedDTO(
                    e.id().value(),
                    e.contributorId()
            );
        }

        if (event instanceof ContributionMade e) {
            return new ContributionMadeDTO(
                    e.id().value(),
                    e.contributorId(),
                    e.caseCode().value(),
                    e.moneyValue().value()
            );
        }

        if (event instanceof ContributionPaid e) {
            return new ContributionPaidDTO(
                    e.id().value(),
                    e.contributorId()
            );
        }

        if (event instanceof ContributionReminded e) {
            return new ContributionRemindedDTO(
                    e.id().value(),
                    e.contributorId()
            );
        }

        throw new IllegalArgumentException("Unknown event type");
    }
}
