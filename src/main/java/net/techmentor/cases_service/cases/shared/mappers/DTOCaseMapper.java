package net.techmentor.cases_service.cases.shared.mappers;

import net.techmentor.cases_service.cases.internal.infrastructure.db.CaseEntity;
import net.techmentor.cases_service.cases.shared.dtos.CaseDTO;
import org.springframework.stereotype.Component;

@Component
public class DTOCaseMapper {

    public CaseDTO toDTO(CaseEntity entity) {
        return new CaseDTO(
                entity.code(),
                entity.title(),
                entity.description(),
                entity.goal(),
                entity.collected(),
                entity.status(),
                entity.acceptZakat(),
                entity.creationDate(),
                entity.lastUpdated(),
                entity.tags(),
                entity.documents(),
                entity.contributions()
        );
    }
}
