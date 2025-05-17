package net.techmentor.cases_service.cases.shared.dtos;

import java.util.UUID;

public record ContributionPaidDTO(
        UUID id,
        UUID contributorId
) implements CaseEventDto {
}
