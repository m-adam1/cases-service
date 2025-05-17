package net.techmentor.cases_service.cases.shared.dtos;

import java.util.UUID;

public record ContributionMadeDTO(
        UUID id,
        UUID contributorId,
        int caseCode,
        int amount
) implements CaseEventDto {
}
