package net.techmentor.cases_service.cases.internal.domain.contracts;

import net.techmentor.cases_service.cases.internal.domain.model.Case.Case;
import net.techmentor.cases_service.cases.internal.domain.model.Case.CaseCode;
import net.techmentor.cases_service.cases.internal.domain.model.Contribution.Contribution;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ICaseRepo {
    CompletableFuture<Integer> nextCaseCode();
    
    CompletableFuture<Case> getByCode(CaseCode caseCode);
    
    CompletableFuture<Void> save(Case case_);
    
    CompletableFuture<Void> delete(CaseCode caseCode);
    
    CompletableFuture<Void> save(Contribution contribution);
    
    CompletableFuture<Contribution> getContributionById(UUID id);
}
