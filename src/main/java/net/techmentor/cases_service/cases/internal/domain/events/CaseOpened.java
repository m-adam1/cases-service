package net.techmentor.cases_service.cases.internal.domain.events;

import net.techmentor.cases_service.cases.internal.domain.model.Case.Case;
import net.techmentor.cases_service.cases.internal.domain.model.Case.CaseCode;

public record CaseOpened(CaseCode caseCode, String title, String description) implements CaseEvent {

    public static CaseOpened from(Case case_) {
        return new CaseOpened(
                case_.getCaseCode(),
                case_.getTitle(),
                case_.getDescription()
        );
    }
}
