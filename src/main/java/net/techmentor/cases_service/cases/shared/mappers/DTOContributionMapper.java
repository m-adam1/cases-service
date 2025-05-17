package net.techmentor.cases_service.cases.shared.mappers;

import net.techmentor.cases_service.cases.internal.infrastructure.db.ContributionEntity;
import net.techmentor.cases_service.cases.shared.dtos.ContributionDTO;
import org.springframework.stereotype.Component;

@Component
public class DTOContributionMapper {
    
    public ContributionDTO toDTO(ContributionEntity entity) {
        return new ContributionDTO(
            entity._id(),
            entity.contributorId(),
            entity.caseCode(),
            entity.amount(),
            entity.status(),
            entity.contributionDate()
        );
    }
}
