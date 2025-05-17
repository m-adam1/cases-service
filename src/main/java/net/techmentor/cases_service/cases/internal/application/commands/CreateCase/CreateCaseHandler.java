package net.techmentor.cases_service.cases.internal.application.commands.CreateCase;

import net.techmentor.cases_service.cases.internal.domain.contracts.ICaseRepo;
import net.techmentor.cases_service.cases.internal.domain.model.Case.Case;
import net.techmentor.cases_service.cases.internal.domain.model.Case.NewCaseProbs;
import net.techmentor.cases_service.cases.internal.domain.model.Case.Status;
import net.techmentor.cases_service.shared.abstractions.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class CreateCaseHandler extends CommandHandler<CreateCase, CaseResponse> {
    private final ICaseRepo caseRepo;

    public CreateCaseHandler(ICaseRepo caseRepo) {
        this.caseRepo = caseRepo;
    }

    @Override
    public CompletableFuture<CaseResponse> handle(CreateCase command) {
        return CompletableFuture.supplyAsync(() -> {

            var newCase = Case.newCase(
                    new NewCaseProbs(
                            caseRepo.nextCaseCode().join(),
                            command.title(),
                            command.description(),
                            command.goal(),
                            command.publish() ? Status.OPENED : Status.DRAFT,
                            command.acceptZakat(),
                            command.documents()
                    )
            );

            caseRepo.save(newCase);
            return new CaseResponse(newCase.getCaseCode().value());
        });
    }
}
