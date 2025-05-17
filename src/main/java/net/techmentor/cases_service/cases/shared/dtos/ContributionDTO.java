package net.techmentor.cases_service.cases.shared.dtos;

public record ContributionDTO(String id,
                              String contributorId,
                              int caseCode,
                              int amount,
                              int status,
                              long contributionDate) {
}
