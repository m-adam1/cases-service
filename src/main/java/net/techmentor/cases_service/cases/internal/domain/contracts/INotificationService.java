package net.techmentor.cases_service.cases.internal.domain.contracts;

import net.techmentor.cases_service.cases.shared.dtos.CaseClosedDTO;
import net.techmentor.cases_service.cases.shared.dtos.CaseOpenedDTO;
import net.techmentor.cases_service.cases.shared.dtos.ContributionMadeDTO;

import java.util.concurrent.CompletableFuture;

public interface INotificationService {
    CompletableFuture<Void> subscribeAccountToCaseUpdates(String token);
    
    CompletableFuture<Void> notifyCaseOpened(CaseOpenedDTO case_);
    
    CompletableFuture<Void> notifyCaseClosed(CaseClosedDTO case_);
    
    CompletableFuture<Void> notifyContributionMade(ContributionMadeDTO contribution);
}
