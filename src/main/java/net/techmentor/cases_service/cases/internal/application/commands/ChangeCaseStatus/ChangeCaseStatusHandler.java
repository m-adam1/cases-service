package net.techmentor.cases_service.cases.internal.application.commands.ChangeCaseStatus;

import net.techmentor.cases_service.cases.internal.domain.contracts.ICaseRepo;
import net.techmentor.cases_service.cases.internal.domain.model.Case.CaseCode;
import net.techmentor.cases_service.shared.abstractions.CommandHandler;
import net.techmentor.cases_service.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ChangeCaseStatusHandler extends CommandHandler<ChangeCaseStatus, Void> {
    private final ICaseRepo caseRepo;

    public ChangeCaseStatusHandler(ICaseRepo caseRepo) {
        this.caseRepo = caseRepo;
    }

    @Override
    public CompletableFuture<Void> handle(ChangeCaseStatus command) {
        return CompletableFuture.runAsync(() -> {
            var case_ = caseRepo.getByCode(new CaseCode(command.caseCode())).join();
            if (case_ == null) {
                throw new NotFoundException("This case is not found");
            }

            if (command.isActionOpen()) {
                case_.open();
            } else {
                case_.close();
            }

            caseRepo.save(case_);
        });
    }
}
