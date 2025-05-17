package net.techmentor.cases_service.cases.internal.infrastructure.repositories.mappers;

import net.techmentor.cases_service.cases.internal.domain.model.Contribution.Contribution;
import net.techmentor.cases_service.cases.internal.domain.model.Contribution.ContributionStatus;
import net.techmentor.cases_service.cases.internal.infrastructure.db.ContributionEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class ContributionMapper {

    public ContributionEntity toDB(Contribution domain) {
        return new ContributionEntity(
                domain.getId().toString(),
                domain.getContributorId().toString(),
                domain.getCaseId().value(),
                domain.getMoneyValue().value(),
                getContributionStatusCode(domain.getContributionStatus()),
                domain.getContributionDate().getTime()
        );
    }

    public Contribution toDomain(ContributionEntity entity) {
        return Contribution.create(
                UUID.fromString(entity.contributorId()),
                entity.caseCode(),
                entity.amount(),
                getContributionStatus(entity.status()),
                new Date(entity.contributionDate())
        );
    }

    private ContributionStatus getContributionStatus(int status) {
        return switch (status) {
            case ContributionEntity.STATUS_PLEDGED -> ContributionStatus.PLEDGED;
            case ContributionEntity.STATUS_PAID -> ContributionStatus.PAID;
            default -> ContributionStatus.CONFIRMED;
        };
    }

    private int getContributionStatusCode(ContributionStatus status) {
        return switch (status) {
            case PLEDGED -> ContributionEntity.STATUS_PLEDGED;
            case PAID -> ContributionEntity.STATUS_PAID;
            case CONFIRMED -> ContributionEntity.STATUS_CONFIRMED;
        };
    }
}
