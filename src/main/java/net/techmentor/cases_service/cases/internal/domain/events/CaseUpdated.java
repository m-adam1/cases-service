package net.techmentor.cases_service.cases.internal.domain.events;

import net.techmentor.cases_service.cases.internal.domain.model.Case.Case;

public record CaseUpdated(Case case_) implements CaseEvent {

    public Case getCase() {
        return case_;
    }
}
