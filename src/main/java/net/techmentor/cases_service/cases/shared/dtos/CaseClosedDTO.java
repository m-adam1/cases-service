package net.techmentor.cases_service.cases.shared.dtos;

public record CaseClosedDTO(
        int caseCode,
        String title,
        int goal,
        int contributionsTotal
) implements CaseEventDto {
}
