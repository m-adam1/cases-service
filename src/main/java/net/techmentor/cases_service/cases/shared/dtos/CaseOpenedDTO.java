package net.techmentor.cases_service.cases.shared.dtos;

public record CaseOpenedDTO(
        int caseCode,
        String title,
        String description
) implements CaseEventDto {
}
