package net.techmentor.cases_service.cases.internal.application.commands.DeleteDraftCase;

import net.techmentor.cases_service.cases.internal.domain.contracts.ICaseRepo;
import net.techmentor.cases_service.cases.internal.domain.model.Case.CaseCode;
import net.techmentor.cases_service.shared.abstractions.CommandHandler;
import net.techmentor.cases_service.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DeleteDraftCaseHandler extends CommandHandler<DeleteDraftCase, Void> {
    private final ICaseRepo caseRepo;

    public DeleteDraftCaseHandler(ICaseRepo caseRepo) {
        this.caseRepo = caseRepo;
    }

    @Override
    public CompletableFuture<Void> handle(DeleteDraftCase command) {
        return CompletableFuture.runAsync(() -> {
            var case_ = caseRepo.getByCode(new CaseCode(command.caseCode())).join();
            if (case_ == null) {
                throw new NotFoundException("This case is not found");
            }
            
            case_.delete(caseRepo);
        });
    }
}
