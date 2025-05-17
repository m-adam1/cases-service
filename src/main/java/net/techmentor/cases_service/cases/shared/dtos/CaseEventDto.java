package net.techmentor.cases_service.cases.shared.dtos;

public sealed interface CaseEventDto permits
        CaseCreatedDTO,
        CaseOpenedDTO,
        CaseClosedDTO,
        ContributionConfirmedDTO,
        ContributionMadeDTO,
        ContributionPaidDTO,
        ContributionRemindedDTO {
}
